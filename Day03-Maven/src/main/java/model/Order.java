package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data

public class Order {
    private String orderId;
    private LocalDate orderDate;
    private String customerId;
    private List<OrderDetail> orderDetails;
}
