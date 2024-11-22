package model.bl;

import controller.exceptions.payment.DuplicatePaymentFoundException;
import controller.exceptions.payment.NoPaymentFoundException;
import controller.exceptions.payment.PaymentIsEmpty;
import lombok.Getter;
import model.da.PaymentDA;
import model.entity.Payment;
import model.tools.CRUD;

import java.util.List;

public class PaymentBL implements CRUD<Payment> {
    @Getter
    private static final PaymentBL paymentBl = new PaymentBL();

    private PaymentBL() {
    }

    @Override
    public Payment save(Payment payment) throws Exception {
        try (PaymentDA paymentDA = new PaymentDA()) {
            if (paymentDA.findById(payment.getPaymentId()) == null) {
                if (payment.getAmount() != 0) {
                    paymentDA.save(payment);
                    return payment;
                } else {
                    throw new PaymentIsEmpty();
                }
            } else {
                throw new DuplicatePaymentFoundException();
            }
        }
    }

    @Override
    public Payment edit(Payment payment) throws Exception {
        try (PaymentDA paymentDA = new PaymentDA()) {
            if (paymentDA.findById(payment.getPaymentId()) != null) {
                paymentDA.edit(payment);
                return payment;
            } else {
                throw new NoPaymentFoundException();
            }
        }
    }

    @Override
    public Payment remove(int id) throws Exception {
        try (PaymentDA paymentDA = new PaymentDA()) {
            Payment payment = paymentDA.findById(id);
            if (payment != null) {
                paymentDA.remove(id);
                return payment;
            } else {
                throw new NoPaymentFoundException();
            }
        }
    }

    @Override
    public List<Payment> findAll() throws Exception {
        try (PaymentDA paymentDA = new PaymentDA()) {
            List<Payment> paymentList = paymentDA.findAll();
            if (!paymentList.isEmpty()) {
                return paymentList;
            } else {
                throw new NoPaymentFoundException();
            }
        }
    }

    @Override
    public Payment findById(int id) throws Exception {
        try (PaymentDA paymentDA = new PaymentDA()) {
            Payment payment = paymentDA.findById(id);
            if (payment != null) {
                return payment;
            } else {
                throw new NoPaymentFoundException();
            }
        }
    }

    public Payment findByOrderID(int id) throws Exception {
        try (PaymentDA paymentDA = new PaymentDA()) {
            Payment payments = paymentDA.findByOrderID(id);
            if (payments != null) {
                return payments;
            } else {
                throw new NoPaymentFoundException();
            }
        }
    }

}
