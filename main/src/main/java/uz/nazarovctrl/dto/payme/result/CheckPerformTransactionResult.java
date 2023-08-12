package uz.nazarovctrl.dto.payme.result;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CheckPerformTransactionResult {
    public boolean allow;
    private DetailResult detail;
}
