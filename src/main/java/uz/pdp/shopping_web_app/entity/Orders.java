package uz.pdp.shopping_web_app.entity;

import  lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private Integer id;
    private LocalDateTime dateTime;
    private UUID userId;
    private boolean status;
    private boolean delivered;

    public String formatDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return dateTime.format(formatter);
    }

    public String checkStatus() {
        if (status) {
            return "Ready To Deliver";
        } else {
            return "New";
        }
    }
    public String checkDeliver() {
        if (delivered) {
            return "Delivered";
        } else {
            return "";
        }
    }

}
