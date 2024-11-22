package controller.exceptions.order;

public class NoOrderFoundException extends Exception {
    public NoOrderFoundException() {
        super("No order found by this id !!!");
    }
}
