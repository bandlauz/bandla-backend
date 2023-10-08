package uz.nazarovctrl.bandla.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import uz.nazarovctrl.bandla.annotations.validation.Password;

@Data
public class CompleteVerificationDTO {
    @NotBlank(message = "Temporary token")
    private String temporaryToken;

    @Password
    private String password;
}
