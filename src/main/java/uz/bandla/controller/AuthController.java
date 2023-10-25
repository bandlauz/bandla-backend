package uz.bandla.controller;

import uz.bandla.annotations.validation.PhoneNumber;
import uz.bandla.dto.Response;
import uz.bandla.dto.auth.request.CheckConfirmationCodeDTO;
import uz.bandla.dto.auth.request.CompleteVerificationDTO;
import uz.bandla.dto.auth.request.LoginDTO;
import uz.bandla.dto.auth.response.CheckCodeResponseDTO;
import uz.bandla.dto.auth.response.RefreshTokenResponseDTO;
import uz.bandla.dto.auth.response.VerifiedResponseDTO;
import uz.bandla.service.AuthService;
import uz.bandla.dto.auth.response.LoginResponseDTO;

import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "Authorization Controller", description = "This controller for authorization")
public class AuthController {
    public final AuthService service;

    @Operation(summary = "Method for checking", description = "This method uses to check phoneNumber status in system")
    @PostMapping("/is-not-verified/{phoneNumber}")
    public ResponseEntity<Response<VerifiedResponseDTO>> isNotVerified(@PathVariable("phoneNumber") @PhoneNumber String phoneNumber) {
        return service.isNotVerified(phoneNumber);
    }

    @Operation(summary = "Method for send code", description = "This method uses to send sms verification code")
    @PostMapping("/verification/send-confirmation-code/{phoneNumber}")
    public ResponseEntity<Response<?>> sendConfirmationCode(@PathVariable("phoneNumber") @PhoneNumber String phoneNumber) {
        return service.sendConfirmationCode(phoneNumber);
    }

    @Operation(summary = "Method for verify", description = "This method uses to verification")
    @PutMapping("/verification/check-confirmation-code")
    public ResponseEntity<Response<CheckCodeResponseDTO>> checkConfirmationCode(@RequestBody @Valid CheckConfirmationCodeDTO dto) {
        return service.checkConfirmationCode(dto);
    }

    @Operation(summary = "Method for set password", description = "This method uses to set password")
    @PutMapping("/verification/complete")
    public ResponseEntity<Response<?>> completeVerification(@RequestBody @Valid CompleteVerificationDTO dto) {
        return service.completeVerification(dto);
    }

    @Operation(summary = "Method for authorization", description = "This method used for Login")
    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponseDTO>> login(@RequestBody @Valid LoginDTO dto) {
        return service.login(dto);
    }

    @Operation(summary = "Method for refresh token", description = "This method used for refresh token")
    @GetMapping("/token/refresh")
    public ResponseEntity<Response<RefreshTokenResponseDTO>> refreshToken(HttpServletRequest request) {
        return service.refreshToken(request);
    }
}
