package uz.pdp.shopping_web_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private UUID id;
    private String name;

    public String makeUrl(String input) {
        String firstChar = String.valueOf(input.charAt(0)).toUpperCase();
        String camelCaseString = "show" + firstChar + input.substring(1);
        String url = camelCaseString + ".jsp";
        return url;
    }
}
