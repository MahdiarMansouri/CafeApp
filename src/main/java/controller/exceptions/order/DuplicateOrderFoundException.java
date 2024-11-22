package controller.exceptions.order;

public class DuplicateOrderFoundException extends Exception {
    public DuplicateOrderFoundException() {
        super("Duplicate order found !!!");
    }
}
