package controller.exceptions.order;

public class OrderIsEmpty extends Exception {
    public OrderIsEmpty() {
        super("Order is empty !!!");
    }
}
