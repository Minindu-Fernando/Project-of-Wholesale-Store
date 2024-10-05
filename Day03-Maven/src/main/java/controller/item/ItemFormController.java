package controller.item;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.Item;
import util.CrudUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnReload;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<Item> tblItem;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItem;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private JFXTextField txtUnitPrice;

    ItemService itemController = new ItemControlller();

    @FXML
    void btnAddOnAction(ActionEvent event) {

        Item item = new Item(txtItem.getText(), txtDescription.getText(), txtPackSize.getText(), Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQtyOnHand.getText()));

        boolean isItemAdded = itemController.addItem(item);
        if (isItemAdded) {
            new Alert(Alert.AlertType.INFORMATION, "Item Added").show();
            loadTable();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (itemController.deleteItem(txtItem.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "" + txtItem.getText() + " : Item Deleted").show();
            loadTable();
        }
        else {
            new Alert(Alert.AlertType.ERROR, "" + txtItem.getText() + " : Item Not Deleted").show();
        }
    }



    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        setValueToText(itemController.searchItem(txtItem.getText()));
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Item item = new Item(txtItem.getText(), txtDescription.getText(), txtPackSize.getText(), Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQtyOnHand.getText()));
            if (itemController.updateItem(item)) {
                new Alert(Alert.AlertType.INFORMATION, "" + txtItem.getText() + " : Item Updated...").show();
                loadTable();
            }

         else {
            new Alert(Alert.AlertType.ERROR, "" + txtItem.getText() + " : Item Not Deleted...Check the Item").show();
        }

    }

    private void loadTable() {

        ObservableList<Item> itemObservableList = itemController.getAllItem();
        tblItem.setItems(itemObservableList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, customer, selectedTableRow) -> {
            if (selectedTableRow != null) {
                setValueToText(selectedTableRow);
            }
        });
    }

    private void setValueToText(Item selectedTableRow) {

        txtItem.setText(selectedTableRow.getItemCode());
        txtDescription.setText(selectedTableRow.getDescription());
        txtPackSize.setText(selectedTableRow.getPackSize());
        txtUnitPrice.setText(String.valueOf(selectedTableRow.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(selectedTableRow.getQtyOnHand()));
    }
}
