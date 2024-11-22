package controller.exceptions.payment;

public class PaymentIsEmpty extends Exception {
    public PaymentIsEmpty() {
        super("Payment is empty !!!");
    }
}
