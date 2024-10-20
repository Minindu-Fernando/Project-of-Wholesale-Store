package repository.custom.impl;

import entity.ItemEntity;
import repository.custom.ItemDao;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(ItemEntity item) {
        System.out.println("Item Repo :" + item);
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
    public boolean update(ItemEntity entity, String s) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        String SQL = "DELETE FROM Item WHERE ItemCode= ?";
        try {
            return CrudUtil.execute(SQL,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<ItemEntity> findAll() {
        return null;
    }
}
