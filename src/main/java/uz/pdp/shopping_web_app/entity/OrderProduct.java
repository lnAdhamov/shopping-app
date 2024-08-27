package uz.pdp.shopping_web_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {
    private Integer amount;
    private UUID productId;
    private Integer orderId;
}
