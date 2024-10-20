package dto;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Item {
    private String itemCode;
    private String description;
    private String packSize;
    private double unitPrice;
    private int qtyOnHand;
}
