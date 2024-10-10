package controller.order;

import controller.item.ItemControlller;
import db.DBConnection;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {
    public Boolean placeOrder(Order order) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement psTm = connection.prepareStatement("INSERT INTO Orders VALUE(?,?,?)");
        psTm.setObject(1, order.getOrderId());
        psTm.setObject(2, order.getOrderDate());
        psTm.setObject(3, order.getCustomerId());

        boolean isOrderAdd = psTm.executeUpdate() > 0;
        if (isOrderAdd) {
            boolean isOrderDetailAdd = OrderDetailController.addOrderDetail(order.getOrderDetails());
            if (isOrderDetailAdd) {

                boolean isUpdateStock = new ItemControlller().updateStock(order.getOrderDetails());
                if (isUpdateStock) {
                    return true;
                }
            }
        }
        return false;
    }
}
