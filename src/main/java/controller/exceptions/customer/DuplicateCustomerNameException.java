package controller.exceptions.customer;

public class DuplicateCustomerNameException extends Exception {
    public DuplicateCustomerNameException() {
        super("Duplicate Customer Name !!!");
    }
}
