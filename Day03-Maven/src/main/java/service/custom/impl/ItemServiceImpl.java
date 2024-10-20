package service.custom.impl;

import entity.ItemEntity;
import javafx.collections.ObservableList;
import dto.Item;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.ItemDao;
import service.custom.ItemService;
import util.DaoType;

public class ItemServiceImpl implements ItemService {

    ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);

    @Override
    public boolean addItem(Item item) {
        System.out.println("ItemService :" + item);
        return itemDao.save(new ModelMapper().map(item, ItemEntity.class));

    }

    @Override
    public boolean updateItem(Item item) {
        ItemEntity  itemEntity = new ModelMapper().map(item, ItemEntity.class);
        return itemDao.update(itemEntity,itemEntity.getItemCode());
    }

    @Override
    public Item searchItem(String id) {
        return null;
    }

    @Override
    public boolean deleteItem(String id) {
        return itemDao.delete(id);
    }

    @Override
    public ObservableList<Item> getAllItem() {
        return null;
    }
}
