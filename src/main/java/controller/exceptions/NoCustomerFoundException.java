package controller.exceptions;

public class NoCustomerFoundException extends Exception {
    public NoCustomerFoundException() {
        super("No customer found !!!");
    }
}
