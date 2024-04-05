package uz.bandla.controller;

import uz.bandla.annotations.validation.PhoneNumber;
import uz.bandla.dto.Response;
import uz.bandla.dto.auth.request.CheckConfirmationCodeDTO;
import uz.bandla.dto.auth.request.CompleteVerificationDTO;
import uz.bandla.dto.auth.request.LoginDTO;
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

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Authorization controller")
public class AuthController {
    public final AuthService service;

    @Operation(summary = "Check phoneNumber status")
    @PostMapping("/is-not-verified/{phoneNumber}")
    public ResponseEntity<Response<Boolean>> isNotVerified(@PathVariable("phoneNumber") @PhoneNumber String phoneNumber) {
        return service.isNotVerified(phoneNumber);
    }

    @Operation(summary = "Send confirmation code")
    @PostMapping("/verification/send-confirmation-code/{phoneNumber}")
    public ResponseEntity<Response<?>> sendConfirmationCode(@PathVariable("phoneNumber") @PhoneNumber String phoneNumber) {
        return service.sendConfirmationCode(phoneNumber);
    }

    @Operation(summary = "Check confirmation code")
    @PutMapping("/verification/check-confirmation-code")
    public ResponseEntity<Response<String>> checkConfirmationCode(@RequestBody @Valid CheckConfirmationCodeDTO dto) {
        return service.checkConfirmationCode(dto);
    }

    @Operation(summary = "Set password to profile")
    @PutMapping("/verification/complete")
    public ResponseEntity<Response<?>> completeVerification(@RequestBody @Valid CompleteVerificationDTO dto) {
        return service.completeVerification(dto);
    }

    @Operation(summary = "Login with phone number and password")
    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponseDTO>> login(@RequestBody @Valid LoginDTO dto) {
        return service.login(dto);
    }

    @Operation(summary = "Get nonce")
    @GetMapping("/nonce")
    public ResponseEntity<Response<String>> getNonce() {
        return service.getNonce();
    }

    @Operation(summary = "Login with telegram")
    @PostMapping("/login-with-telegram")
    public ResponseEntity<Response<LoginResponseDTO>> loginWithTelegram(@RequestBody @Valid String nonce) {
        return service.loginWithTelegram(nonce);
    }

    @Operation(summary = "Get new access token")
    @GetMapping("/refresh-token")
    public ResponseEntity<Response<String>> refreshToken(HttpServletRequest request) {
        return service.refreshToken(request);
    }
}