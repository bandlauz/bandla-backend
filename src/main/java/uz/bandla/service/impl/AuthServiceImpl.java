package uz.bandla.service.impl;

import uz.bandla.dto.Response;
import uz.bandla.dto.GoodResponse;
import uz.bandla.dto.auth.request.CheckConfirmationCodeDTO;
import uz.bandla.dto.auth.request.CompleteVerificationDTO;
import uz.bandla.dto.auth.request.LoginDTO;
import uz.bandla.dto.auth.response.CheckCodeResponseDTO;
import uz.bandla.dto.auth.response.RefreshTokenResponseDTO;
import uz.bandla.dto.auth.response.VerifiedResponseDTO;
import uz.bandla.exp.auth.PasswordAlreadySavedException;
import uz.bandla.exp.auth.ProfileLockedException;
import uz.bandla.exp.auth.ProfileStatusIncorrectException;
import uz.bandla.exp.auth.TokenExpiredException;
import uz.bandla.favor.ProfileFavor;
import uz.bandla.security.jwt.JwtService;
import uz.bandla.security.profile.ProfileDetails;
import uz.bandla.security.profile.ProfileDetailsService;
import uz.bandla.util.MD5;
import uz.bandla.dto.auth.response.LoginResponseDTO;
import uz.bandla.entity.ProfileEntity;
import uz.bandla.enums.ProfileStatus;
import uz.bandla.service.AuthService;
import uz.bandla.service.VerificationService;

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
public class AuthServiceImpl implements AuthService {
    private final ProfileDetailsService profileDetailsService;
    private final JwtService jwtService;
    private final VerificationService verificationService;
    private final ProfileFavor profileFavor;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<Response<VerifiedResponseDTO>> isNotVerified(String phoneNumber) {
        Optional<ProfileEntity> optional = profileFavor.findByPhoneNumber(phoneNumber);

        if (optional.isPresent()) {
            ProfileEntity profile = optional.get();
            if (!profile.getStatus().equals(ProfileStatus.NOT_VERIFIED)) {
                return GoodResponse.ok(new VerifiedResponseDTO(false));
            }
        } else {
            ProfileEntity profile = new ProfileEntity();
            profile.setPhoneNumber(phoneNumber);
            profileFavor.save(profile);
        }

        return GoodResponse.ok(new VerifiedResponseDTO(true));
    }

    @Override
    public ResponseEntity<Response<?>> sendConfirmationCode(String phoneNumber) {
        profileFavor.findByPhoneNumberOrElseThrow(phoneNumber);

        verificationService.sendConfirmationCode(phoneNumber);
        return GoodResponse.ok("SUCCESS");
    }

    @Override
    public ResponseEntity<Response<CheckCodeResponseDTO>> checkConfirmationCode(CheckConfirmationCodeDTO dto) {
        ProfileEntity profile = profileFavor.findByPhoneNumberOrElseThrow(dto.getPhoneNumber());
        if (!profile.getStatus().equals(ProfileStatus.NOT_VERIFIED)) {
            throw new ProfileStatusIncorrectException();
        }

        verificationService.checkConfirmationCode(dto);
        String temporaryToken = jwtService.generateTemporaryToken(profile.getPhoneNumber());

        return GoodResponse.ok(new CheckCodeResponseDTO(temporaryToken));
    }

    @Override
    public ResponseEntity<Response<?>> completeVerification(CompleteVerificationDTO dto) {
        String temporaryToken = dto.getTemporaryToken();
        if (jwtService.isTokenExpired(temporaryToken)) {
            throw new TokenExpiredException(jwtService.getTokenExpiredMessage(temporaryToken));
        }

        String username = jwtService.extractTemporaryTokenUsername(temporaryToken);
        ProfileEntity profile = profileFavor.findByPhoneNumberOrElseThrow(username);
        if (!profile.getStatus().equals(ProfileStatus.NOT_VERIFIED)) {
            throw new ProfileStatusIncorrectException();
        }

        if (profile.getPassword() != null) {
            throw new PasswordAlreadySavedException();
        }

        String password = passwordEncoder.encode(MD5.encode(dto.getPassword()));
        profileFavor.savePassword(profile.getId(), password);

        return GoodResponse.ok("SUCCESS");
    }

    @Override
    public ResponseEntity<Response<LoginResponseDTO>> login(LoginDTO dto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getPhoneNumber(),
                        MD5.encode(dto.getPassword())));
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

    @Override
    public ResponseEntity<Response<RefreshTokenResponseDTO>> refreshToken(HttpServletRequest request) {
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

        return GoodResponse.ok(new RefreshTokenResponseDTO(accessToken));
    }
}