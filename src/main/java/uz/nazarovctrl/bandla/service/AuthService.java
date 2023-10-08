package uz.nazarovctrl.bandla.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import uz.nazarovctrl.bandla.dto.Response;
import uz.nazarovctrl.bandla.dto.auth.request.CheckConfirmationCodeDTO;
import uz.nazarovctrl.bandla.dto.auth.request.CompleteVerificationDTO;
import uz.nazarovctrl.bandla.dto.auth.request.LoginDTO;
import uz.nazarovctrl.bandla.dto.auth.response.LoginResponseDTO;

public interface AuthService {
    ResponseEntity<Response<?>> prepareLogin(String phoneNumber);

    ResponseEntity<Response<?>> checkConfirmationCode(CheckConfirmationCodeDTO dto);

    ResponseEntity<Response<String>> completeVerification(CompleteVerificationDTO dto);

    ResponseEntity<Response<LoginResponseDTO>> login(LoginDTO dto);

    ResponseEntity<Response<?>> refreshToken(HttpServletRequest request);
}
