package uz.nazarovctrl.dto.payme.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Account {
    @SerializedName("order_id")
    private Long orderId;
}
