package uz.bandla.dto.profile;

import uz.bandla.annotations.validation.NullOrNotBlank;
import uz.bandla.annotations.validation.Url;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyProfileDTO {
    @NotBlank(message = "First name invalid")
    private String firstName;

    @NullOrNotBlank(message = "Last name invalid")
    private String lastName;

    @Url(canBeNull = true, message = "Photo url invalid")
    private String photoUrl;
}