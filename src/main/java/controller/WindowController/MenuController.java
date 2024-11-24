package controller.WindowController;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Setter;
import model.bl.CustomerBL;
import model.bl.ItemBL;
import model.entity.Customer;
import model.entity.Item;
import model.entity.OrderLine;
import model.entity.enums.Category;


import javax.swing.text.TabableView;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @Setter
    private Stage stage;

    @FXML
    private Label totalPriceLbl;

    @FXML
    private TextField customerNameTxt, customerFamilyTxt, phoneNumberTxt;

    @FXML
    private Button coldDrinksBtn, hotDrinksBtn, coffeeBtn, cakeBtn, milkshakeBtn, iceCreamBtn, takeOrderBtn;

    @FXML
    private TableView<Item> menuTbl;

    @FXML
    private TableView<OrderLine> orderTbl;

    @FXML
    private TableColumn<Item, Integer> idMenuTbl, idOrderTbl, priceMenuTbl, priceOrderTbl, countOrderTbl, totalOrderTbl;

    @FXML
    private TableColumn<Item, String> itemNameMenuTbl, descriptionMenuTbl, itemNameOrderTbl;

    //    private ObservableArray<Item> orderList;
    private ObservableList<ObservableList<String>> addToOrder;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderTbl.setEditable(true);
        String category = "Coffee";
        try {
            showMenuOnTable(category);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        coldDrinksBtn.setOnAction(event -> {
            try {
                clearTable();
                showMenuOnTable("ColdDrink");
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        hotDrinksBtn.setOnAction(event -> {
            try {
                clearTable();
                showMenuOnTable("HotDrink");
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        cakeBtn.setOnAction(event -> {
            try {
                clearTable();
                showMenuOnTable("Cake");
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        coffeeBtn.setOnAction(event -> {
            try {
                clearTable();
                showMenuOnTable("Coffee");
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        milkshakeBtn.setOnAction(event -> {
            try {
                clearTable();
                showMenuOnTable("Milkshake");
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        iceCreamBtn.setOnAction(event -> {
            try {
                clearTable();
                showMenuOnTable("IceCream");
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        addToOrder = FXCollections.observableArrayList();
        menuTbl.setOnMouseClicked(event -> {
            Item item = menuTbl.getSelectionModel().getSelectedItem();
            OrderLine orderLine = OrderLine
                    .builder()
                    .id(item.getItemId())
                    .item(item)
                    .build();

            orderLine.setCount(1);

//            orderLine.setTotalPrice(orderLine.getCount() * orderLine.getItem().getPrice());

            System.out.println(orderLine);

//            if (  ) {
//
//            } else {
//
//                idOrderTbl.setCellValueFactory(new PropertyValueFactory<>("itemId"));
//                itemNameOrderTbl.setCellValueFactory(new PropertyValueFactory<>("name"));
//                priceOrderTbl.setCellValueFactory(new PropertyValueFactory<>("price"));
//
//
//            }

        });

        takeOrderBtn.setOnAction(event -> {
            CustomerBL customerBL = new CustomerBL();
            Customer customer = Customer.builder()
                    .firstName(customerNameTxt.getText())
                    .lastName(customerFamilyTxt.getText())
                    .phoneNumber(phoneNumberTxt.getText())
                    .build();
            try {
                customerBL.save(customer);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }

            
        });


    }



    private void updateCount(OrderLine orderLine) {
        orderLine.setCount(orderLine.getCount() + 1);
    }

    private void clearTable() {
        menuTbl.getItems().clear();
    }

    private void showMenuOnTable(String category) throws Exception {
        ItemBL itemBL = new ItemBL();
        List<Item> itemList = itemBL.findByCategory(category);
        ObservableList<Item> observableList = FXCollections.observableList(itemList);

        idMenuTbl.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameMenuTbl.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionMenuTbl.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceMenuTbl.setCellValueFactory(new PropertyValueFactory<>("price"));

        menuTbl.setItems(observableList);
    }
//
}
