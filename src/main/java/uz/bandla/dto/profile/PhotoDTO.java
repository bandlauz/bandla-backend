package uz.bandla.dto.profile;

import lombok.Getter;
import uz.bandla.annotations.validation.Url;

@Getter
public class PhotoDTO {

    @Url(canBeNull = true)
    private String photoUrl;
}
