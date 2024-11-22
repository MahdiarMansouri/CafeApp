package model.da;


import model.entity.Order;
import model.entity.Payment;
import model.entity.enums.PaymentMethod;
import model.entity.enums.PaymentStatus;
import model.tools.CRUD;
import model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PaymentDA implements AutoCloseable, CRUD<Payment> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public PaymentDA() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Payment save(Payment payment) throws Exception {
        payment.setPaymentId(ConnectionProvider.getConnectionProvider().getNextId("PAYMENT_SEQ"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO PAYMENT_TBL (PAYMENT_ID, PAYMENT_METHOD, PAYMENT_STATUS, AMOUNT, ORDER_ID)"
        );
        preparedStatement.setInt(1, payment.getPaymentId());
        preparedStatement.setString(2, payment.getPaymentMethod().toString());
        preparedStatement.setString(3, payment.getPaymentStatus().toString());
        preparedStatement.setInt(4, payment.getAmount());
        preparedStatement.setInt(5, payment.getOrder().getOrderId());
        preparedStatement.execute();
        return payment;
    }

    @Override
    public Payment edit(Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE PAYMENT_TBL SET  PAYMENT_METHOD=?, AMOUNT=?, PAYMRN_STATUS=?, ORDER_ID=? WHERE PAYMENT_ID=?"
        );
        preparedStatement.setString(1, payment.getPaymentMethod().toString());
        preparedStatement.setString(2, payment.getPaymentStatus().toString());
        preparedStatement.setInt(3, payment.getAmount());
        preparedStatement.setInt(4, payment.getOrder().getOrderId());
        preparedStatement.setInt(5, payment.getPaymentId());
        preparedStatement.execute();
        return payment;
    }

    @Override
    public Payment remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PAYMENT_TBL WHERE PAYMENT_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Payment> findAll() throws Exception {
        List<Payment> paymentList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM PAYMENT_TBL ORDER BY PAYMENT_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Order order = new Order();
            order.setOrderId(resultSet.getInt("ORDER_ID"));

            Payment payment = Payment
                    .builder()
                    .paymentId(resultSet.getInt("PAYMENT_ID"))
                    .amount(resultSet.getInt("AMOUNT"))
                    .paymentMethod(PaymentMethod.valueOf(resultSet.getString("PAYMENT_METHOD")))
                    .paymentStatus(PaymentStatus.valueOf(resultSet.getString("PAYMENT_STATUS")))
                    .order(order)
                    .build();

            paymentList.add(payment);
        }

        return paymentList;
    }

    @Override
    public Payment findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM PAYMENT_TBL WHERE PAYMENT_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Payment payment = null;
        if (resultSet.next()) {
            Order order = new Order();
            order.setOrderId(resultSet.getInt("ORDER_ID"));

            payment = Payment
                    .builder()
                    .paymentId(resultSet.getInt("PAYMENT_ID"))
                    .amount(resultSet.getInt("AMOUNT"))
                    .paymentMethod(PaymentMethod.valueOf(resultSet.getString("PAYMENT_METHOD")))
                    .paymentStatus(PaymentStatus.valueOf(resultSet.getString("PAYMENT_STATUS")))
                    .order(order)
                    .build();
        }
        return payment;
    }

    public Payment findByOrderID(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM PAYMENT_TBL WHERE ORDER_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Payment payment = null;
        if (resultSet.next()) {
            Order order = new Order();
            order.setOrderId(resultSet.getInt("ORDER_ID"));

            payment = Payment
                    .builder()
                    .paymentId(resultSet.getInt("PAYMENT_ID"))
                    .amount(resultSet.getInt("AMOUNT"))
                    .paymentMethod(PaymentMethod.valueOf(resultSet.getString("PAYMENT_METHOD")))
                    .paymentStatus(PaymentStatus.valueOf(resultSet.getString("PAYMENT_STATUS")))
                    .order(order)
                    .build();
        }
        return payment;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
