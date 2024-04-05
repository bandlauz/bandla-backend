package uz.bandla.dto.auth.request;

import uz.bandla.annotations.validation.PhoneNumber;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @PhoneNumber
    private String phoneNumber;

    @NotBlank(message = "Password must not be blank")
    private String password;
}
