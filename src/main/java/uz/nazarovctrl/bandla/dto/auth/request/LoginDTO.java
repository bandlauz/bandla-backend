package uz.nazarovctrl.bandla.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import uz.nazarovctrl.bandla.annotations.validation.PhoneNumber;

@Getter
@Setter
public class LoginDTO {
    @PhoneNumber
    private String phoneNumber;

    @NotBlank
    private String password;
}
