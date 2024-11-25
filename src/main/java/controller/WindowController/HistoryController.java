package controller.WindowController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Setter;
import model.bl.OrderBL;
import model.bl.PaymentBL;
import model.entity.*;
import model.entity.enums.OrderStatus;
import model.entity.enums.PaymentMethod;
import model.entity.enums.PaymentStatus;


import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    @Setter
    private Stage stage;

    @FXML
    private Label totalPriceLbl;

    @FXML
    private Button paymentProcessBtn;

    @FXML
    private TableView<Order> ordersTbl;

    @FXML
    private TableColumn<Order, Integer> idOrdersTbl, totalPriceOrdersTbl;

    @FXML
    private TableColumn<Order, OrderStatus> statusOrdersTbl;

    @FXML
    private TableColumn<Order, String> itemsOrdersTbl;

    @FXML
    private TableView<OrderItem> orderTbl;

    @FXML
    private TableColumn<OrderItem, Integer> idOrderTbl, countOrderTbl, unitPriceOrderTbl, totalOrderTbl;

    @FXML
    private TableColumn<OrderItem, String> itemNameOrderTbl;

    @FXML
    private TableView<Payment> paymentsTbl;

    @FXML
    private TableColumn<Payment, Integer> idPaymentsTbl, orderIDPaymentsTbl;

    @FXML
    private TableColumn<Payment, PaymentStatus> statusPaymentsTbl;

    @FXML
    private TableColumn<Payment, PaymentMethod> methodPaymentsTbl;
    private ObservableList<OrderItem> itemList;
    private Order selectedOrder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showOrdersOnTable();
            showPaymentsOnTable();
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }

        itemList = FXCollections.observableArrayList();

        ordersTbl.setOnMouseClicked(event -> {
            Order orderSelected = ordersTbl.getSelectionModel().getSelectedItem();
            if (orderSelected != null) {
                showSelectedOrderOnTable(orderSelected);
                this.selectedOrder = orderSelected;
            }
        });

        paymentProcessBtn.setOnAction(event -> {
            PaymentMethod paymentMethod = showMethodChoices();
            PaymentStatus paymentStatus = showStatusChoices();
            if (paymentMethod != null && paymentStatus != null) {

                Payment payment = Payment.builder()
                        .paymentStatus(paymentStatus)
                        .paymentMethod(paymentMethod)
                        .amount(Integer.parseInt(totalPriceLbl.getText()))
                        .order(this.selectedOrder)
                        .build();
                PaymentBL paymentBL = new PaymentBL();
                try {
                    paymentBL.save(payment);
                    showPaymentsOnTable();
                } catch (Exception e) {
                    showErrorAlert(e.getMessage());
                }
                if (paymentStatus == PaymentStatus.Completed) {
                    try {
                        updateOrderStatus(OrderStatus.Closed, payment.getOrder());
                    } catch (Exception e) {
                        showErrorAlert(e.getMessage());
                    }
                }

            }
        });
    }

    private void showPaymentsOnTable() {
        PaymentBL paymentBL = new PaymentBL();
        List<Payment> paymentList = null;
        try {
            paymentList = paymentBL.findAll();
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }

        ObservableList<Payment> paymentsObservable = FXCollections.observableList(paymentList);

        idPaymentsTbl.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        methodPaymentsTbl.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        statusPaymentsTbl.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        orderIDPaymentsTbl.setCellValueFactory(new PropertyValueFactory<>("orderID"));

        if (!paymentsObservable.isEmpty()) {
            paymentsTbl.setItems(paymentsObservable);
        }
    }

    private void updateOrderStatus(OrderStatus orderStatus, Order order) throws Exception {
        selectedOrder.setStatus(orderStatus);
        order.setStatus(orderStatus);
        OrderBL orderBL = new OrderBL();
        orderBL.edit(order);
        ordersTbl.refresh();
    }

    private PaymentStatus showStatusChoices() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Status Choice");
        alert.setHeaderText("Choose a Status");
        alert.setContentText("Please choose one of the statuses below:");

        ButtonType completedStatusBtn = new ButtonType("Completed");
        ButtonType failedStatusBtn = new ButtonType("Failed");
        ButtonType cancelButton = ButtonType.CANCEL;

        alert.getButtonTypes().setAll(completedStatusBtn, failedStatusBtn, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();


        PaymentStatus paymentStatus = null;
        if (result.isPresent()) {
            if (result.get() == completedStatusBtn) {
                paymentStatus = PaymentStatus.Completed;
                System.out.println("Completed Selected.");
            } else if (result.get() == failedStatusBtn) {
                paymentStatus = PaymentStatus.Failed;
                System.out.println("Failed selected.");
            } else {
                System.out.println("Operation cancelled.");
            }
        }
        return paymentStatus;
    }

    public PaymentMethod showMethodChoices() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Method Choice");
        alert.setHeaderText("Choose a Method");
        alert.setContentText("Please choose one of the methods below:");

        ButtonType casheMethodButton = new ButtonType("Cash");
        ButtonType creditCardMethodButton = new ButtonType("Credit Card");
        ButtonType cancelButton = ButtonType.CANCEL;

        alert.getButtonTypes().setAll(casheMethodButton, creditCardMethodButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();


        PaymentMethod paymentMethod = null;
        if (result.isPresent()) {
            if (result.get() == casheMethodButton) {
                paymentMethod = PaymentMethod.Cash;
                System.out.println("Cash selected.");
            } else if (result.get() == creditCardMethodButton) {
                paymentMethod = PaymentMethod.CreditCard;
                System.out.println("Credit Card selected.");
            } else {
                System.out.println("Operation cancelled.");
            }
        }
        return paymentMethod;
    }

    private void showSelectedOrderOnTable(Order order) {
        List<OrderItem> itemList = order.getProducts();
        idOrderTbl.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameOrderTbl.setCellValueFactory(new PropertyValueFactory<>("name"));
        countOrderTbl.setCellValueFactory(new PropertyValueFactory<>("count"));
        unitPriceOrderTbl.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalOrderTbl.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        this.itemList = FXCollections.observableArrayList(itemList);
        orderTbl.getItems().clear();
        orderTbl.getItems().addAll(itemList);
        updateTotalPrice();

    }

    private void showOrdersOnTable() {
        OrderBL orderBL = new OrderBL();
        List<Order> orderList = null;
        try {
            orderList = orderBL.findAll();
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }

        ObservableList<Order> observableList = FXCollections.observableList(orderList);

        idOrdersTbl.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        itemsOrdersTbl.setCellValueFactory(new PropertyValueFactory<>("products"));
        totalPriceOrdersTbl.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        statusOrdersTbl.setCellValueFactory(new PropertyValueFactory<>("status"));
        if (!observableList.isEmpty()) {
            ordersTbl.setItems(observableList);
        }
    }

    private void updateTotalPrice() {

        Integer totalPrice = itemList.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
        totalPriceLbl.setText(String.valueOf(totalPrice));
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.show();
    }


}




