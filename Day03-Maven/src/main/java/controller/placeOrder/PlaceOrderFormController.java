package controller.placeOrder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.customer.CustomerController;
import controller.item.ItemControlller;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.CartTM;
import model.Customer;
import model.Item;
import model.OrderDetail;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<CartTM> tblOrders;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtItemDescription;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private JFXTextField txtStock;

    @FXML
    private JFXTextField txtUnitPrice;
    ObservableList<CartTM> cart = FXCollections.observableArrayList();

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        Double total = unitPrice * qty;

        cart.add(new CartTM(cmbItemCode.getValue(), txtItemDescription.getText(), qty, unitPrice, total));
        tblOrders.setItems(cart);
        calculateNetTotal();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId = txtOrderId.getText();
        String customerId = cmbCustomerId.getValue();
        String orderDate = lblDate.getText();

        List<OrderDetail> orderDetails = new ArrayList<>();

        for (CartTM cartTM : cart) {

            String itemCode = cartTM.getItemCode();
            Integer qty = cartTM.getQty();
            orderDetails.add(new OrderDetail(orderId, itemCode, qty, 0.0));
        }

    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + " : " + now.getMinute() + " : " + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void loadCustomerIds() {
        cmbCustomerId.setItems(new CustomerController().getCustomerIds());
    }

    private void loadItemcode() {
        cmbItemCode.setItems(new ItemControlller().getItemCode());
    }

    private void loadItemdata(String itemCode) {
        Item item = new ItemControlller().searchItem(itemCode);

        txtItemDescription.setText(item.getDescription());
        txtStock.setText(String.valueOf(item.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
    }

    private void loadCustomerdata(String id) {
        Customer customer = new CustomerController().searchCustomer(id);
        txtName.setText(customer.getName());
        txtCity.setText(customer.getCity());
        txtSalary.setText(String.valueOf(customer.getSalary()));
    }

    public void calculateNetTotal() {
        Double total = 0.0;
        for (CartTM cartTM : cart) {
            total += cartTM.getTotal();
        }
        ;
        lblNetTotal.setText(total.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newValue) -> {
            loadItemdata(newValue);
        });
        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener(((observableValue, s, t1) -> {
            loadCustomerdata(t1);
        }));
        loadCustomerIds();
        loadDateAndTime();
        loadItemcode();
    }
}
