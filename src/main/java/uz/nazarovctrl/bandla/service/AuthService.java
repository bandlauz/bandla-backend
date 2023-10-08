package uz.nazarovctrl.bandla.service;

import uz.nazarovctrl.bandla.component.ResponseGenerator;
import uz.nazarovctrl.bandla.dto.Response;
import uz.nazarovctrl.bandla.dto.auth.request.LoginDTO;
import uz.nazarovctrl.bandla.dto.auth.response.LoginResponseDTO;
import uz.nazarovctrl.bandla.dto.auth.request.CompleteVerificationDTO;
import uz.nazarovctrl.bandla.dto.auth.request.CheckConfirmationCodeDTO;
import uz.nazarovctrl.bandla.entity.ProfileEntity;
import uz.nazarovctrl.bandla.enums.Status;
import uz.nazarovctrl.bandla.exp.auth.*;
import uz.nazarovctrl.bandla.security.jwt.JwtService;
import uz.nazarovctrl.bandla.security.profile.ProfileDetails;
import uz.nazarovctrl.bandla.security.profile.ProfileDetailsService;
import uz.nazarovctrl.bandla.util.MD5;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final ProfileDetailsService profileDetailsService;
    private final JwtService jwtService;
    private final VerificationService verificationService;
    private final ProfileService profileService;
    private final ResponseGenerator responseGenerator;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<Response<?>> prepareLogin(String phoneNumber) {
        Optional<ProfileEntity> optional = profileService.findByPhoneNumber(phoneNumber);

        if (optional.isPresent() &&
                !optional.get().getStatus().equals(Status.NOT_VERIFIED)) {
            return responseGenerator.generateOk("isNotVerified", false);
        }

        if (optional.isEmpty()) {
            ProfileEntity profile = new ProfileEntity();
            profile.setPhoneNumber(phoneNumber);
            profileService.save(profile);
        }

        verificationService.sendConfirmationCode(phoneNumber);
        return responseGenerator.generateOk("isNotVerified", true);
    }

    public ResponseEntity<Response<?>> checkConfirmationCode(CheckConfirmationCodeDTO dto) {
        ProfileEntity profile = profileService.findByPhoneNumberOrElseThrow(dto.getPhoneNumber());
        if (!profile.getStatus().equals(Status.NOT_VERIFIED)) {
            throw new ProfileStatusIncorrectException();
        }
        verificationService.checkConfirmationCode(dto);

        return responseGenerator.generateOk("temporaryToken",
                jwtService.generateTemporaryToken(profile.getPhoneNumber()));
    }

    public ResponseEntity<Response<String>> completeVerification(CompleteVerificationDTO dto) {
        String temporaryToken = dto.getTemporaryToken();
        if (jwtService.isTokenExpired(temporaryToken)) {
            throw new TokenExpiredException(jwtService.getTokenExpiredMessage(temporaryToken));
        }

        String username = jwtService.extractTemporaryTokenUsername(temporaryToken);
        ProfileEntity profile = profileService.findByPhoneNumberOrElseThrow(username);
        if (!profile.getStatus().equals(Status.NOT_VERIFIED)) {
            throw new ProfileStatusIncorrectException();
        }

        if (profile.getPassword() != null) {
            throw new PasswordAlreadySavedException();
        }

        String password = passwordEncoder.encode(MD5.md5(dto.getPassword()));
        profileService.savePassword(profile.getId(), password);
        return responseGenerator.generateSuccess();
    }

    public ResponseEntity<Response<LoginResponseDTO>> login(LoginDTO dto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getPhoneNumber(),
                        MD5.md5(dto.getPassword())));
        ProfileDetails profile = (ProfileDetails) authenticate.getPrincipal();

        String accessToken = jwtService.generateAccessToken(profile.getUsername());
        String refreshToken = jwtService.generateRefreshToken(profile.getUsername());

        Response<LoginResponseDTO> response = new Response<>(LoginResponseDTO.builder()
                .authorities(profile.getAuthorities())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response<?>> refreshToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build();
        }

        String refreshToken = authorizationHeader.substring("Bearer ".length());
        if (jwtService.isTokenExpired(refreshToken)) {
            throw new TokenExpiredException(jwtService.getTokenExpiredMessage(refreshToken));
        }

        String username = jwtService.extractRefreshTokenUsername(refreshToken);
        UserDetails userDetails = profileDetailsService.loadUserByUsername(username);

        if (!userDetails.isAccountNonLocked() || !userDetails.isEnabled()) {
            throw new ProfileLockedException();
        }

        String accessToken = jwtService.generateAccessToken(userDetails.getUsername());
        return responseGenerator.generateOk("accessToken", accessToken);
    }
}