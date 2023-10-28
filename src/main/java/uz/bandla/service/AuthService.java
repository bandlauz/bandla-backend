package uz.bandla.service;

import uz.bandla.dto.Response;
import uz.bandla.dto.auth.request.CheckConfirmationCodeDTO;
import uz.bandla.dto.auth.request.CompleteVerificationDTO;
import uz.bandla.dto.auth.request.LoginDTO;
import uz.bandla.dto.auth.request.TelegramLoginDTO;
import uz.bandla.dto.auth.response.LoginResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<Response<Boolean>> isNotVerified(String phoneNumber);

    ResponseEntity<Response<?>> sendConfirmationCode(String phoneNumber);

    ResponseEntity<Response<String>> checkConfirmationCode(CheckConfirmationCodeDTO dto);

    ResponseEntity<Response<?>> completeVerification(CompleteVerificationDTO dto);

    ResponseEntity<Response<LoginResponseDTO>> login(LoginDTO dto);

    ResponseEntity<Response<String>> refreshToken(HttpServletRequest request);

    ResponseEntity<Response<Boolean>> checkTelegramAccount(TelegramLoginDTO dto);

    ResponseEntity<Response<LoginResponseDTO>> loginWithTelegram(TelegramLoginDTO dto);
}
