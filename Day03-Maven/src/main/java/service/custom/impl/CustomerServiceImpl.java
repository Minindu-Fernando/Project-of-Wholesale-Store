package service.custom.impl;

import entity.CustomerEntity;
import javafx.collections.ObservableList;
import dto.Customer;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.CustomerDao;
import service.custom.CustomerService;
import util.DaoType;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTMER);

    @Override
    public boolean addCustomer(Customer customer) {
        System.out.println("Service : " + customer);
        customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTMER);
        CustomerEntity  entity = new ModelMapper().map(customer, CustomerEntity.class);
        return customerDao.save(entity);

    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public Customer searchCustomer(String id) {
        return null;
    }

    @Override
    public boolean deleteCustomer(String id) {
      return customerDao.delete(id);

    }

    @Override
    public ObservableList<Customer> getAllCustomers() {
        return null;
    }
}
