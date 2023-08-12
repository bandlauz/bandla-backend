package uz.nazarovctrl.dto.payme.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Error {
    private Integer code;
    private String message;
    private String data;
}
