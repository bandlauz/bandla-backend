package uz.bandla.service.impl;

import uz.bandla.dto.Response;
import uz.bandla.dto.GoodResponse;
import uz.bandla.dto.auth.request.CheckConfirmationCodeDTO;
import uz.bandla.dto.auth.request.CompleteVerificationDTO;
import uz.bandla.dto.auth.request.LoginDTO;
import uz.bandla.entity.NonceEntity;
import uz.bandla.enums.ProfileRole;
import uz.bandla.exp.NotValidException;
import uz.bandla.exp.auth.*;
import uz.bandla.repository.NonceRepository;
import uz.bandla.repository.ProfileRepository;
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
import uz.bandla.util.NonceUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final ProfileDetailsService profileDetailsService;
    private final JwtService jwtService;
    private final VerificationService verificationService;
    private final ProfileRepository profileRepository;
    private final NonceRepository nonceRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Override
    public ResponseEntity<Response<Boolean>> isNotVerified(String phoneNumber) {
        Optional<ProfileEntity> optional = profileRepository.findByPhoneNumberAndIsVisibleTrue(phoneNumber);

        if (optional.isPresent()) {
            ProfileEntity profile = optional.get();
            if (!profile.getStatus().equals(ProfileStatus.NOT_VERIFIED)) {
                return GoodResponse.ok(Boolean.FALSE);
            }
        } else {
            ProfileEntity profile = new ProfileEntity();
            profile.setPhoneNumber(phoneNumber);
            profileRepository.save(profile);
        }

        return GoodResponse.ok(Boolean.TRUE);
    }

    @Override
    public ResponseEntity<Response<?>> sendConfirmationCode(String phoneNumber) {
        profileRepository.existsByPhoneNumberAndIsVisibleTrueOrElseThrow(phoneNumber);

        verificationService.sendConfirmationCode(phoneNumber);
        return GoodResponse.okMessage("SUCCESS");
    }

    @Override
    public ResponseEntity<Response<String>> checkConfirmationCode(CheckConfirmationCodeDTO dto) {
        ProfileEntity profile = profileRepository.getByPhoneNumberAndIsVisibleTrue(dto.getPhoneNumber());
        if (!profile.getStatus().equals(ProfileStatus.NOT_VERIFIED)) {
            throw new ProfileStatusIncorrectException();
        }

        verificationService.checkConfirmationCode(dto);
        String temporaryToken = jwtService.generateTemporaryToken(profile.getPhoneNumber());
        return GoodResponse.ok(temporaryToken);
    }

    @Override
    public ResponseEntity<Response<?>> completeVerification(CompleteVerificationDTO dto) {
        String temporaryToken = dto.getTemporaryToken();
        if (jwtService.isTokenExpired(temporaryToken)) {
            throw new TokenExpiredException(jwtService.getTokenExpiredMessage(temporaryToken));
        }

        String username = jwtService.extractTemporaryTokenUsername(temporaryToken);
        ProfileEntity profile = profileRepository.getByPhoneNumberAndIsVisibleTrue(username);
        if (!profile.getStatus().equals(ProfileStatus.NOT_VERIFIED)) {
            throw new ProfileStatusIncorrectException();
        }

        if (profile.getPassword() != null) {
            throw new PasswordAlreadySavedException();
        }

        String password = passwordEncoder.encode(MD5.encode(dto.getPassword()));
        profileRepository.savePasswordById(profile.getId(), password);

        return GoodResponse.okMessage("SUCCESS");
    }

    @Override
    public ResponseEntity<Response<LoginResponseDTO>> login(LoginDTO dto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getPhoneNumber(),
                        MD5.encode(dto.getPassword())));
        ProfileDetails profile = (ProfileDetails) authenticate.getPrincipal();

        LoginResponseDTO responseDTO = generateLoginResponse(profile.getUsername(), profile.getRole());
        return GoodResponse.ok(responseDTO);
    }

    @Override
    public ResponseEntity<Response<String>> refreshToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new AuthHeaderNotFoundException();
        }

        String refreshToken = authorizationHeader.substring("Bearer " .length());
        if (jwtService.isTokenExpired(refreshToken)) {
            throw new TokenExpiredException(jwtService.getTokenExpiredMessage(refreshToken));
        }

        String username = jwtService.extractRefreshTokenUsername(refreshToken);
        UserDetails userDetails = profileDetailsService.loadUserByUsername(username);

        if (!userDetails.isAccountNonLocked() || !userDetails.isEnabled()) {
            throw new ProfileLockedException();
        }

        String accessToken = jwtService.generateAccessToken(userDetails.getUsername());
        return GoodResponse.ok(accessToken);
    }

    @Override
    public ResponseEntity<Response<String>> getNonce() {
        NonceEntity nonceEntity = new NonceEntity();

        nonceEntity = nonceRepository.save(nonceEntity);

        return GoodResponse.ok(nonceEntity.getId());
    }

    @Override
    public ResponseEntity<Response<LoginResponseDTO>> loginWithTelegram(String nonce) {
        NonceEntity nonceEntity = nonceRepository.getReferenceById(nonce);
        if (!NonceUtil.isValid(nonceEntity) || nonceEntity.getProfile() == null) {
            throw new NotValidException("Nonce not valid");
        }
        nonceEntity.setIsUsed(true);
        nonceRepository.save(nonceEntity);

        ProfileEntity profile = nonceEntity.getProfile();
        LoginResponseDTO responseDTO = generateLoginResponse(profile.getPhoneNumber(), profile.getRole());
        return GoodResponse.ok(responseDTO);
    }

    private LoginResponseDTO generateLoginResponse(String phoneNumber, ProfileRole role) {
        String accessToken = jwtService.generateAccessToken(phoneNumber);
        String refreshToken = jwtService.generateRefreshToken(phoneNumber);

        return LoginResponseDTO.builder()
                .role(role)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}