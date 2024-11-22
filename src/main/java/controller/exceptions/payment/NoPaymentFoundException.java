package controller.exceptions.payment;

public class NoPaymentFoundException extends Exception {
    public NoPaymentFoundException() {
        super("No payment found !!!");
    }
}
