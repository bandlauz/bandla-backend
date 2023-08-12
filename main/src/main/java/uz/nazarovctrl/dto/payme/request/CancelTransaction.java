package uz.nazarovctrl.dto.payme.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.nazarovctrl.enums.payme.OrderCancelReason;

@AllArgsConstructor
@Getter
public class CancelTransaction {
    private String id;
    private OrderCancelReason reason;
}
