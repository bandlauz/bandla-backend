package uz.bandla.service;

import uz.bandla.dto.Response;
import uz.bandla.dto.auth.request.CheckConfirmationCodeDTO;
import uz.bandla.dto.auth.request.CompleteVerificationDTO;
import uz.bandla.dto.auth.request.LoginDTO;
import uz.bandla.dto.auth.response.LoginResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<Response<?>> prepareLogin(String phoneNumber);

    ResponseEntity<Response<?>> checkConfirmationCode(CheckConfirmationCodeDTO dto);

    ResponseEntity<Response<String>> completeVerification(CompleteVerificationDTO dto);

    ResponseEntity<Response<LoginResponseDTO>> login(LoginDTO dto);

    ResponseEntity<Response<?>> refreshToken(HttpServletRequest request);
}
