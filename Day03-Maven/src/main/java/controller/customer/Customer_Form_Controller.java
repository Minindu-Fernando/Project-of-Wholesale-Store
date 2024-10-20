package controller.customer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import dto.Customer;
import service.ServiceFactory;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class Customer_Form_Controller implements Initializable {

    public JFXButton btnItem;
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
    private JFXComboBox<String> cmbTitle;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colBirthDay;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private DatePicker dobBirthDay;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtProvince;

    @FXML
    private JFXTextField txtSalary;

    CustomerService customerController = new CustomerController();
    service.custom.CustomerService customerService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);


    @FXML
    void btnAddOnAction(ActionEvent event) {

        Customer customer = new Customer(txtId.getText(), cmbTitle.getValue(), txtName.getText(), dobBirthDay.getValue(), Double.parseDouble(txtSalary.getText()), txtAddress.getText(), txtCity.getText(), txtProvince.getText(), txtPostalCode.getText());
        customerService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
        boolean isCustomerAdd = customerService.addCustomer(customer);

        if (isCustomerAdd) {
            new Alert(Alert.AlertType.INFORMATION, "Customer Added").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR, "Customer Not Added").show();

        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        if (customerService.deleteCustomer(txtId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "" + txtId.getText() + " : Customer Deleted").show();
            loadTable();
        } else {
            new Alert(Alert.AlertType.ERROR, "" + txtId.getText() + " : Customer Not Deleted").show();
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        setValueToText(customerController.searchCustomer(txtId.getText()));
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Customer customer = new Customer(txtId.getText(), cmbTitle.getValue(), txtName.getText(), dobBirthDay.getValue(), Double.parseDouble(txtSalary.getText()), txtAddress.getText(), txtCity.getText(), txtProvince.getText(), txtPostalCode.getText());
            if (customerController.updateCustomer(customer)) {
                new Alert(Alert.AlertType.INFORMATION, "" + txtId.getText() + " : Customer Updated").show();
                loadTable();
            }
         else {
            new Alert(Alert.AlertType.ERROR, "" + txtId.getText() + " : Customer Deleted").show();
        }
    }

    private void loadTable() {
        ObservableList<Customer> customerObservableList = customerController.getAllCustomers();
        tblCustomer.setItems(customerObservableList);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colBirthDay.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> customerTitleList = FXCollections.observableArrayList();
        customerTitleList.add("Mr");
        customerTitleList.add("Mrs");
        customerTitleList.add("Miss");
        customerTitleList.add("Ms");
        cmbTitle.setItems(customerTitleList);
        loadTable();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, customer, selectedTableRow) -> {
            if (selectedTableRow != null) {
                setValueToText(selectedTableRow);
            }
        });

    }

    private void setValueToText(Customer selectedTableRow) {

        txtId.setText(selectedTableRow.getId());
        txtName.setText(selectedTableRow.getName());
        cmbTitle.setValue(selectedTableRow.getTitle());
        txtCity.setText(selectedTableRow.getCity());
        txtAddress.setText(selectedTableRow.getAddress());
        txtSalary.setText(selectedTableRow.getSalary().toString());
        txtPostalCode.setText(selectedTableRow.getPostalCode());
        txtProvince.setText(selectedTableRow.getProvince());
        dobBirthDay.setValue(selectedTableRow.getDob());
    }

    @FXML
    public void btnItemOnAction(ActionEvent actionEvent) {
        try {
            // Load the Item_form FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Item_form.fxml"));
            AnchorPane root = fxmlLoader.load();

            // Create a new scene with the loaded FXML
            Scene scene = new Scene(root);

            // Get the current stage (window) using the button event
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
