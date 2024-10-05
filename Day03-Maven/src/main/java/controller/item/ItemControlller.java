package controller.item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Item;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemControlller implements ItemService{
    @Override
    public boolean addItem(Item item) {
        String SQL = "INSERT INTO Item VALUES(?,?,?,?,?)";

        try {
            return CrudUtil.execute(SQL,
                    item.getItemCode(),
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getQtyOnHand());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateItem(Item item) {
        String SQL = "UPDATE Item SET Description=?,PackSize=?,UnitPrice=?,QtyOnHand=? WHERE ItemCode =?";
        try {
            return CrudUtil.execute(SQL,
                     item.getDescription(),
                     item.getPackSize(),
                     item.getUnitPrice(),
                     item.getQtyOnHand(),
                     item.getItemCode()
             );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item searchItem(String id) {
        String SQL = "SELECT * From Item WHERE ItemCode= ?";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL, id);
            resultSet.next();
            return new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5)
                );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteItem(String id) {
        String SQL = "DELETE FROM Item WHERE ItemCode= ?";
        try {
          return CrudUtil.execute(SQL,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Item> getAllItem() {
        String SQL = "SELECT * FROM Item";
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()) {
              itemObservableList.add(new Item(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                ));
            }
            return itemObservableList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
