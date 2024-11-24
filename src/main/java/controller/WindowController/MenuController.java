package controller.WindowController;

import javafx.collections.FXCollections;
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
import model.bl.OrderBL;
import model.entity.Customer;
import model.entity.Item;
import model.entity.Order;
import model.entity.OrderItem;
import model.entity.enums.OrderStatus;


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
    private Button coldDrinksBtn, hotDrinksBtn, coffeeBtn, cakeBtn, milkshakeBtn, iceCreamBtn, takeOrderBtn, resetBtn, removeBtn;

    @FXML
    private TableView<Item> menuTbl;

    @FXML
    private TableView<OrderItem> orderTbl;

    @FXML
    private TableColumn<OrderItem, Integer> idOrderTbl, countOrderTbl, priceOrderTbl, totalOrderTbl, idMenuTbl, itemNameMenuTbl, descriptionMenuTbl, priceMenuTbl;

    @FXML
    private TableColumn<OrderItem, String> itemNameOrderTbl;

    private ObservableList<OrderItem> orderItems;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idOrderTbl.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameOrderTbl.setCellValueFactory(new PropertyValueFactory<>("name"));
        countOrderTbl.setCellValueFactory(new PropertyValueFactory<>("count"));
        priceOrderTbl.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalOrderTbl.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        orderItems = FXCollections.observableArrayList();
        orderTbl.setItems(orderItems);

        try {
            showMenuOnTable("Coffee");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setupCategoryButton(coldDrinksBtn, "ColdDrink");
        setupCategoryButton(hotDrinksBtn, "HotDrink");
        setupCategoryButton(cakeBtn, "Cake");
        setupCategoryButton(coffeeBtn, "Coffee");
        setupCategoryButton(milkshakeBtn, "Milkshake");
        setupCategoryButton(iceCreamBtn, "IceCream");

        menuTbl.setOnMouseClicked(event -> {
            Item item = menuTbl.getSelectionModel().getSelectedItem();
            if (item != null) {
                addItemToOrder(item);
            }
        });

        orderTbl.setOnMouseClicked(event -> {
            OrderItem orderItemSelected = orderTbl.getSelectionModel().getSelectedItem();
            if (orderItemSelected != null) {
                removeBtn.setOnAction(e -> removeOrderItem(orderItemSelected));
            }
        });
        takeOrderBtn.setOnAction(event -> handleTakeOrder());
        resetBtn.setOnAction(event -> resetOrderTable());
    }

    private void removeOrderItem(OrderItem orderItemSelected) {
        orderTbl.getItems().remove(orderItemSelected);
        updateTotalPrice();
    }

    private void setupCategoryButton(Button button, String category) {
        button.setOnAction(event -> {
            try {
                clearTable();
                showMenuOnTable(category);
            } catch (Exception e) {
                showErrorAlert(e.getMessage());
            }
        });
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

    private void clearTable() {
        menuTbl.getItems().clear();
    }

    private void resetOrderTable() {
        orderItems.clear();
        totalPriceLbl.setText("0");
    }

    private void addItemToOrder(Item item) {
        OrderItem orderItem = OrderItem.builder()
                .itemId(item.getItemId())
                .name(item.getName())
                .description(item.getDescription())
                .category(item.getCategory())
                .isAvailable(true)
                .count(1)
                .price(item.getPrice())
                .totalPrice(item.getPrice())
                .build();

        boolean itemExists = false;
        for (OrderItem existingItem : orderItems) {
            if (existingItem.getItemId() == orderItem.getItemId()) {
                existingItem.setCount(existingItem.getCount() + 1);
                existingItem.updateTotalPrice(existingItem.getCount());
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            orderItems.add(orderItem);
        }
        orderTbl.refresh();
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        Integer totalPrice = orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
        totalPriceLbl.setText(String.valueOf(totalPrice));
    }

    private void handleTakeOrder() {
        try {
            CustomerBL customerBL = new CustomerBL();
            Customer customer = Customer.builder()
                    .firstName(customerNameTxt.getText())
                    .lastName(customerFamilyTxt.getText())
                    .phoneNumber(phoneNumberTxt.getText())
                    .build();
            System.out.println("Customer: " + customer);

            customerBL.save(customer);

            ObservableList<OrderItem> orderItems = orderTbl.getItems();
            Order order = Order.builder()
                    .customer(customer)
                    .products(orderItems)
                    .totalPrice(Integer.valueOf(totalPriceLbl.getText()))
                    .status(OrderStatus.Open)
                    .build();
            System.out.println("Order: " + order);

            OrderBL orderBL = new OrderBL();

            orderBL.save(order);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order Submitted Successfully.", ButtonType.OK);
            alert.showAndWait();
            resetOrderTable();

        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.show();
    }


}




