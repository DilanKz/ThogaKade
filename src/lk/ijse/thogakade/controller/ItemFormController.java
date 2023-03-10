package lk.ijse.thogakade.controller;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.thogakade.entity.Items;
import lk.ijse.thogakade.model.CustomerModel;
import lk.ijse.thogakade.repository.ItemRepository;
import lk.ijse.thogakade.entity.Items;
import lk.ijse.thogakade.util.Navigation;
import lk.ijse.thogakade.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
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
    private TableView<Items> tblCustomer;

    @FXML
    private TableColumn<Items, String> colID;

    @FXML
    private TableColumn<Items, String> colName;

    @FXML
    private TableColumn<Items, Double> colAddress;

    @FXML
    private TableColumn<Items, Integer> colSalary;

    @FXML
    private TableColumn<?, ?> colAction;

    private ItemRepository itemRepository=new ItemRepository();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        double address = Double.parseDouble(txtPrice.getText());
        int salary = Integer.parseInt(txtQty.getText());

        Items items = new Items(id, name, address, salary);
        try {
            boolean isAdded =itemRepository.addItem(items);
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
            Items items = itemRepository.getItem(id);
            if (items != null) {
                fillData(items);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(Items items) {
        txtId.setText(items.getId());
        txtName.setText(items.getName());
        txtPrice.setText(String.valueOf(items.getPrice()));
        txtQty.setText(String.valueOf(items.getQty()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("qty"));

        loadAll();
    }

    private void loadAll(){
        ArrayList<Items> items= (ArrayList<Items>) itemRepository.getAll();
        ObservableList observableList= FXCollections.observableArrayList(items);
        tblCustomer.setItems(observableList);
    }
}
