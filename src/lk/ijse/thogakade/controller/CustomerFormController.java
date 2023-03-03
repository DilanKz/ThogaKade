package lk.ijse.thogakade.controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.thogakade.repository.CustomerRepository;
import lk.ijse.thogakade.entity.Customer;
import lk.ijse.thogakade.util.Navigation;
import lk.ijse.thogakade.util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtSalary;

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

    private CustomerRepository customerRepository=new CustomerRepository();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());

        //Customer customer = new Customer(id, name, address, salary);
        try {
            boolean isAdded = customerRepository.addCustomer(
                    new Customer(
                            id,
                            name,
                            address,
                            salary
                    )
            );
                    //CustomerModel.save(customer);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (Exception e) {
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
            Customer customer =customerRepository.getCustomer(id);
            //CustomerModel.search(id);
            if (customer != null) {
                fillData(customer);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(Customer customer) {
        txtId.setText(customer.getId());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(String.valueOf(customer.getSalary()));
    }
}
