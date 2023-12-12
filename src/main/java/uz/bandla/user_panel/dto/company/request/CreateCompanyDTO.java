package uz.bandla.user_panel.dto.company.request;

import uz.bandla.annotations.validation.Url;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCompanyDTO {
    @NotBlank(message = "Name required")
    String name;
    @NotBlank(message = "Address required")
    String address;
    @Url
    String photoUrl;
}