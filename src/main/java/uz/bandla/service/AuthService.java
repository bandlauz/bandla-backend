package uz.bandla.service;

import uz.bandla.dto.Response;
import uz.bandla.dto.auth.request.CheckConfirmationCodeDTO;
import uz.bandla.dto.auth.request.CompleteVerificationDTO;
import uz.bandla.dto.auth.request.LoginDTO;
import uz.bandla.dto.auth.response.CheckCodeResponseDTO;
import uz.bandla.dto.auth.response.LoginResponseDTO;
import uz.bandla.dto.auth.response.RefreshTokenResponseDTO;
import uz.bandla.dto.auth.response.VerifiedResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<Response<VerifiedResponseDTO>> isNotVerified(String phoneNumber);

    ResponseEntity<Response<?>> sendConfirmationCode(String phoneNumber);

    ResponseEntity<Response<CheckCodeResponseDTO>> checkConfirmationCode(CheckConfirmationCodeDTO dto);

    ResponseEntity<Response<?>> completeVerification(CompleteVerificationDTO dto);

    ResponseEntity<Response<LoginResponseDTO>> login(LoginDTO dto);

    ResponseEntity<Response<RefreshTokenResponseDTO>> refreshToken(HttpServletRequest request);
}
