package repository.custom.impl;

import entity.CustomerEntity;
import repository.custom.CustomerDao;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity customer) {
        System.out.println("Repositiry :" + customer);
        String SQL = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            return  CrudUtil.execute(
                    SQL,
                    customer.getId(),
                    customer.getTitle(),
                    customer.getName(),
                    customer.getDob(),
                    customer.getSalary(),
                    customer.getAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalCode()
            );
        } catch (SQLException e) {

        }

        return false;
    }

    @Override
    public boolean update(CustomerEntity customer, String id) {
        String SQL = "UPDATE Customer SET CustName = ?,CustTitle=?,DOB=?,salary=?,CustAddress=?,City=?,Province=?,PostalCode=? WHERE CustId =?";
        try {
            return CrudUtil.execute(
                    SQL,
                    customer.getName(),
                    customer.getTitle(),
                    customer.getDob(),
                    customer.getSalary(),
                    customer.getAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalCode(),
                    id
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean delete(String id) {
        String SQL = "DELETE FROM customer WHERE CustID=?";

        try {
            return CrudUtil.execute(SQL,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<CustomerEntity> findAll() {

        return null;
    }
}
