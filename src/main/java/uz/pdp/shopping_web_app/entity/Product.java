package uz.pdp.shopping_web_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.shopping_web_app.repo.CategoryRepo;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private UUID id;
    private String name;
    private Integer price;
    private UUID categoryId;
    private byte[] photo;

    public String categoryName() {
        return CategoryRepo.findById(categoryId).getName();
    }

    public Category findCategory() {
        return CategoryRepo.findById(categoryId);
    }
}
