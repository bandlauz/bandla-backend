package uz.bandla.dto.profile;

import lombok.Getter;
import uz.bandla.annotations.validation.Url;

@Getter
public class PhotoDTO {

    @Url(canBeNull = true, message = "Photo url invalid")
    private String photoUrl;
}
