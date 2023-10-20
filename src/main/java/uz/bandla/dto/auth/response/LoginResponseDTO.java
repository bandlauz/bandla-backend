package uz.bandla.dto.auth.response;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private Collection<SimpleGrantedAuthority> authorities;
    private String accessToken;
    private String refreshToken;
}
