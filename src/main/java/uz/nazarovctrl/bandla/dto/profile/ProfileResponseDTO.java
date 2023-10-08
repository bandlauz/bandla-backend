package uz.nazarovctrl.bandla.dto.profile;

import uz.nazarovctrl.bandla.enums.ProfileRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileResponseDTO {
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private ProfileRole role;
    private Boolean isVisible;
    private LocalDateTime createdDate;
}
