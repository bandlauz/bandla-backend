package ctrl.nazarov.web.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileVerifyDTO {
    private String phoneNumber;

    @Size(min = 4, message = "Code required")
    private String code;

    public ProfileVerifyDTO(String phone) {
        phoneNumber = phone;
    }
}
