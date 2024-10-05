package controller.item;

import javafx.collections.ObservableList;
import model.Item;

public interface ItemService {
    boolean addItem(Item item);
    boolean updateItem(Item item);
    Item searchItem(String id);
    boolean deleteItem(String id);
    ObservableList<Item> getAllItem();//Select All Item eka.
}
