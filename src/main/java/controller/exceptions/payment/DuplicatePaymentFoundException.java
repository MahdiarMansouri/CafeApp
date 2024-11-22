package controller.exceptions.payment;

public class DuplicatePaymentFoundException extends Exception {
    public DuplicatePaymentFoundException() {
        super("Duplicate payment found !!!");
    }
}
