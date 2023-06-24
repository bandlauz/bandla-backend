package uz.nazarovctrl.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCreateDTO {
    @NotBlank(message = "Full name required")
    private String name;

    private String phoneNumber;

    @Size(min = 8, message = "Password required")
    private String password;
}
