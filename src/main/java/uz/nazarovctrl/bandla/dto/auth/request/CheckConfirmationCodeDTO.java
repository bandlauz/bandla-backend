package uz.nazarovctrl.bandla.dto.auth.request;

import lombok.Data;
import uz.nazarovctrl.bandla.annotations.validation.Code;
import uz.nazarovctrl.bandla.annotations.validation.PhoneNumber;

@Data
public class CheckConfirmationCodeDTO {
    @PhoneNumber
    private String phoneNumber;

    @Code
    private String code;
}
