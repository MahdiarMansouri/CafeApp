package controller.exceptions.customer;

public class NoCustomerFoundException extends Exception {
    public NoCustomerFoundException() {
        super("No customer found !!!");
    }
}
