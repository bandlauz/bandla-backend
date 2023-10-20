package uz.bandla.dto.auth.request;

import uz.bandla.annotations.validation.Code;
import uz.bandla.annotations.validation.PhoneNumber;

import lombok.Data;

@Data
public class CheckConfirmationCodeDTO {
    @PhoneNumber
    private String phoneNumber;

    @Code
    private String code;
}
