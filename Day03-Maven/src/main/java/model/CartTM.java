package model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CartTM {
    private String itemCode;
    private String description;
    private Integer qty;
    private double unitPrice;
    private Double total;

}
