package uz.bandla.dto.auth.request;

import uz.bandla.annotations.validation.Password;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompleteVerificationDTO {
    @NotBlank(message = "Temporary token")
    private String temporaryToken;

    @Password
    private String password;
}
