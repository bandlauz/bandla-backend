package uz.bandla.dto.profile;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyProfileResponseDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private String phoneNumber;
    private LocalDateTime createdDate;
}