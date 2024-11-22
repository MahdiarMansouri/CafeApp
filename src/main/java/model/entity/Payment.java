package model.entity;

import com.google.gson.Gson;
import jdk.net.SocketFlow;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import model.entity.enums.PaymentMethod;
import model.entity.enums.PaymentStatus;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Payment {
    private int paymentId;
    private int amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private Order order;

    public void processPayment() {
    }

    public void updatePaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

