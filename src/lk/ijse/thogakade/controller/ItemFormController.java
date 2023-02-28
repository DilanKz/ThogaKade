package lk.ijse.thogakade.controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.thogakade.model.CustomerModel;
import lk.ijse.thogakade.to.Customer;
import lk.ijse.thogakade.util.Navigation;
import lk.ijse.thogakade.util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class ItemFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtPrice.getText();
        double salary = Double.parseDouble(txtQty.getText());

        Customer customer = new Customer(id, name, address, salary);
        try {
            boolean isAdded = CustomerModel.save(customer);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, pane);
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {

    }

    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            Customer customer = CustomerModel.search(id);
            if (customer != null) {
                fillData(customer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(Customer customer) {
        txtId.setText(customer.getId());
        txtName.setText(customer.getName());
        txtPrice.setText(customer.getAddress());
        txtQty.setText(String.valueOf(customer.getSalary()));
    }
}
