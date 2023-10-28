package uz.bandla.dto.auth.response;

import lombok.*;
import uz.bandla.enums.ProfileRole;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private ProfileRole role;
    private String accessToken;
    private String refreshToken;
}
