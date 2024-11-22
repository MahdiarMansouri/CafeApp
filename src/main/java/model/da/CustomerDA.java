package model.da;

import model.entity.Customer;
import model.entity.enums.Role;
import model.tools.CRUD;
import model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomerDA implements AutoCloseable, CRUD<Customer> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public CustomerDA() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Customer save(Customer customer) throws Exception {
        customer.setCustomerId(ConnectionProvider.getConnectionProvider().getNextId("CUSTOMER_SEQ"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO CUSTOMER_TBL (CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_FAMILY, PHONE_NUMBER) VALUES (?,?,?,?)"
        );
        preparedStatement.setInt(1, customer.getCustomerId());
        preparedStatement.setString(2, customer.getFirstName());
        preparedStatement.setString(3, customer.getLastName());
        preparedStatement.setString(4, customer.getPhoneNumber());
        preparedStatement.execute();
        return customer;
    }

    @Override
    public Customer edit(Customer customer) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE CUSTOMER_TBL SET CUSTOMER_NAME=?, CUSTOMER_FAMILY=?, PHONE_NUMBER=? WHERE CUSTOMER_ID=?"
        );
        preparedStatement.setString(1, customer.getFirstName());
        preparedStatement.setString(2, customer.getLastName());
        preparedStatement.setString(3, customer.getPhoneNumber());
        preparedStatement.setInt(4, customer.getCustomerId());
        preparedStatement.execute();
        return customer;
    }

    @Override
    public Customer remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM CUSTOMER_TBL WHERE CUSTOMER_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Customer> findAll() throws Exception {
        List<Customer> customerList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER_TBL ORDER BY CUSTOMER_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Customer customer = Customer
                    .builder()
                    .customerId(resultSet.getInt("CUSTOMER_ID"))
                    .firstName(resultSet.getString("CUSTOMER_NAME"))
                    .lastName(resultSet.getString("CUSTOMER_FAMILY"))
                    .phoneNumber(resultSet.getString("PHONE_NUMBER"))
                    .build();

            customerList.add(customer);
        }

        return customerList;
    }

    @Override
    public Customer findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER_TBL WHERE CUSTOMER_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = null;
        if (resultSet.next()) {
            customer = Customer
                    .builder()
                    .customerId(resultSet.getInt("CUSTOMER_ID"))
                    .firstName(resultSet.getString("CUSTOMER_NAME"))
                    .lastName(resultSet.getString("CUSTOMER_FAMILY"))
                    .phoneNumber(resultSet.getString("PHONE_NUMBER"))
                    .build();
        }
        return customer;
    }

    public Customer findByCustomerName(String customerName) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER_TBL WHERE CUSTOMER_NAME LIKE %?% ");
        preparedStatement.setString(1, customerName);
        ResultSet resultSet = preparedStatement.executeQuery();

        Customer customer = null;
        if (resultSet.next()) {
            customer = Customer
                    .builder()
                    .customerId(resultSet.getInt("CUSTOMER_ID"))
                    .firstName(resultSet.getString("CUSTOMER_NAME"))
                    .lastName(resultSet.getString("CUSTOMER_FAMILY"))
                    .phoneNumber(resultSet.getString("PHONE_NUMBER"))
                    .build();
        }
        return customer;
    }


    public Customer findByCustomerFamily(String lastName) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER_TBL WHERE CUSTOMER_FAMILY LIKE %?%");
        preparedStatement.setString(1, lastName);
        ResultSet resultSet = preparedStatement.executeQuery();

        Customer customer = null;
        if (resultSet.next()) {
            customer = Customer
                    .builder()
                    .customerId(resultSet.getInt("CUSTOMER_ID"))
                    .firstName(resultSet.getString("CUSTOMER_NAME"))
                    .lastName(resultSet.getString("CUSTOMER_FAMILY"))
                    .phoneNumber(resultSet.getString("PHONE_NUMBER"))
                    .build();
        }
        return customer;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

}
