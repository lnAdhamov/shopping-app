package uz.pdp.shopping_web_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.shopping_web_app.config.ConnectionPoolManager;
import uz.pdp.shopping_web_app.repo.BasketProductRepo;
import uz.pdp.shopping_web_app.repo.BasketRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketProduct {
    private UUID productId;
    private UUID basketId;
    private Integer amount;



}
