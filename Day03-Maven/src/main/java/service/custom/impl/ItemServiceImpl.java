package service.custom.impl;

import javafx.collections.ObservableList;
import dto.Item;
import service.custom.ItemService;

public class ItemServiceImpl implements ItemService {
    @Override
    public boolean addItem(Item item) {
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
    }

    @Override
    public Item searchItem(String id) {
        return null;
    }

    @Override
    public boolean deleteItem(String id) {
        return false;
    }

    @Override
    public ObservableList<Item> getAllItem() {
        return null;
    }
}
