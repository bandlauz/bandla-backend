package uz.bandla.dto.company.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import uz.bandla.annotations.validation.Url;

@Getter
public class CreateCompanyDTO {
    @NotBlank(message = "Name required")
    String name;
    @NotBlank(message = "Address required")
    String address;
    @Url
    String photoUrl;
}