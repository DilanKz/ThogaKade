package lk.ijse.thogakade.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.thogakade.entity.Items;
import lk.ijse.thogakade.entity.OrderDetails;
import lk.ijse.thogakade.entity.Orders;
import lk.ijse.thogakade.model.CustomerModel;
import lk.ijse.thogakade.model.ItemModel;
import lk.ijse.thogakade.model.OrderModel;
import lk.ijse.thogakade.model.PlaceOrderModel;
import lk.ijse.thogakade.repository.CustomerRepository;
import lk.ijse.thogakade.repository.ItemRepository;
import lk.ijse.thogakade.repository.OrdersRepository;
import lk.ijse.thogakade.to.CartDetail;
import lk.ijse.thogakade.to.Item;
import lk.ijse.thogakade.to.PlaceOrder;
import lk.ijse.thogakade.util.Navigation;
import lk.ijse.thogakade.util.Routes;
import lk.ijse.thogakade.view.tm.PlaceOrderTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


public class PlaceOrderFormController implements Initializable {
    @FXML
    private AnchorPane pane;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderDate;

    @FXML
    private JFXComboBox cmbCustomerId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private JFXComboBox cmbItemCode;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private TextField txtQty;

    @FXML
    private TableView<PlaceOrderTM> tblOrderCart;

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private TableColumn colTotal;

    @FXML
    private TableColumn colAction;

    //Creating Required Repositories
    private CustomerRepository customerRepository=new CustomerRepository();
    private ItemRepository itemRepository=new ItemRepository();
    private OrdersRepository ordersRepository=new OrdersRepository();

    private ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadOrderDate();
        loadCustomerIds();
        loadNextOrderId();
        loadItemCodes();
        setCellValueFactory();
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = String.valueOf(cmbItemCode.getValue());
        int qty = Integer.parseInt(txtQty.getText());
        String desc = lblDescription.getText();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = unitPrice * qty;
        Button btnDelete = new Button("Delete");

        txtQty.setText("");

        if (!obList.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(code)) {
                    qty += (int) colQty.getCellData(i);
                    total = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);
                    tblOrderCart.refresh();
                    return;
                }
            }
        }

        /* set delete button to some action before it put on obList */
        btnDelete.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {
                PlaceOrderTM tm = tblOrderCart.getSelectionModel().getSelectedItem();
                /*
                netTot = Double.parseDouble(txtNetTot.getText());
                netTot = netTot - tm.getTotalPrice();
                */

                tblOrderCart.getItems().removeAll(tblOrderCart.getSelectionModel().getSelectedItem());
            }
        });
        obList.add(new PlaceOrderTM(code, desc, qty, unitPrice, total, btnDelete));
        tblOrderCart.setItems(obList);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId = lblOrderId.getText();
        String customerId = String.valueOf(cmbCustomerId.getValue());

        ArrayList<CartDetail> cartDetails = new ArrayList<>();
        ArrayList<OrderDetails> orderDetails=new ArrayList<>();
        Orders orders = new Orders();
        orders.setId("O001");
        orders.setDate(String.valueOf(LocalDate.now()));
        orders.setCustomer(customerRepository.getCustomer(customerId));

        /* load all cart items' to orderDetails arrayList */
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            /* get each row details to (PlaceOrderTm)tm in each time and add them to the orderDetails */
            PlaceOrderTM tm = obList.get(i);
            cartDetails.add(new CartDetail(orderId, tm.getCode(), tm.getQty(), tm.getDescription(), tm.getUnitPrice()));

            OrderDetails orderDetails1 = new OrderDetails();
            orderDetails1.setItems(itemRepository.getItem(tm.getCode()));
            orderDetails1.setPrice(tm.getUnitPrice());
            orderDetails1.setQty(tm.getQty());
            orderDetails1.setOrders(orders);

            orderDetails.add(orderDetails1);
        }



        PlaceOrder placeOrder = new PlaceOrder(customerId, orderId, cartDetails);
        try {
            boolean isPlaced = ordersRepository.addOrder(orders,orderDetails);
            //PlaceOrderModel.placeOrder(placeOrder);
            if (isPlaced) {
                /* to clear table */
                obList.clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!, Order id is: " +orders.getId()).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadNextOrderId() {
        try {
            String orderId = OrderModel.generateNextOrderId();
            //lblOrderId.setText(orderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadOrderDate() {
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadCustomerIds() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = (ArrayList<String>) customerRepository.getIds();
            //CustomerModel.loadCustomerIds();

            for (String id : idList) {
                observableList.add(id);
            }
            cmbCustomerId.setItems(observableList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemCodes() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = (ArrayList<String>) itemRepository.getIds();//ItemModel.loadItemCodes();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbItemCode.setItems(observableList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        colAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, pane);
    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
        String code = String.valueOf(cmbItemCode.getValue());
        try {
            //Item item = ItemModel.search(code);
            Items item = itemRepository.getItem(code);
            fillItemFields(item);

            txtQty.requestFocus();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void fillItemFields(Items item) {
        lblDescription.setText(item.getName());
        lblUnitPrice.setText(String.valueOf(item.getPrice()));
        lblQtyOnHand.setText(String.valueOf(item.getQty()));
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
        btnAddToCartOnAction(actionEvent);
    }
}
