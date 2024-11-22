package controller.exceptions;

public class DuplicateCustomerNameException extends Exception {
    public DuplicateCustomerNameException() {
        super("Duplicate Customer Name !!!");
    }
}
