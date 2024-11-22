package model.da;

import com.google.gson.reflect.TypeToken;
import model.entity.Customer;
import model.entity.Item;
import model.entity.Order;
import model.entity.enums.OrderStatus;
import model.tools.CRUD;
import model.tools.ConnectionProvider;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderDA implements AutoCloseable, CRUD<Order> {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    Gson gson = new Gson();


    public OrderDA() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Order save(Order order) throws Exception {
        order.setOrderId(ConnectionProvider.getConnectionProvider().getNextId("ORDER_SEQ"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO ORDER_TBL (ORDER_ID, ITEMS, TOTAL_PRICE, STATUS, CUSTOMER_ID) VALUES (?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, order.getOrderId());
        preparedStatement.setString(2, order.getProducts().toString());
        preparedStatement.setInt(3, order.getTotalPrice());
        preparedStatement.setString(4, String.valueOf(order.getStatus()));
        preparedStatement.setInt(5, order.getCustomer().getCustomerId());
        preparedStatement.execute();
        return order;
    }

    @Override
    public Order edit(Order order) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE ORDER_TBL SET  ITEMS=?, TOTAL_PRICE=?, STATUS=?, CUSTOMER_ID=? WHERE ORDER_ID=?"
        );
        preparedStatement.setString(1, order.getProducts().toString());
        preparedStatement.setInt(2, order.getTotalPrice());
        preparedStatement.setString(3, String.valueOf(order.getStatus()));
        preparedStatement.setInt(4, order.getCustomer().getCustomerId());
        preparedStatement.setInt(5, order.getOrderId());
        preparedStatement.execute();
        return order;
    }

    @Override
    public Order remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM ORDER_TBL WHERE ORDER_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Order> findAll() throws Exception {
        List<Order> orderList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM ORDER_TBL ORDER BY ORDER_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Gson gson = new Gson();
            String itemStrings = resultSet.getString("ITEMS");
            List<Item> items = gson.fromJson(itemStrings, new TypeToken<List<Item>>() {
            }.getType());
            Customer customer = new Customer();
            customer.setCustomerId(resultSet.getInt("CUSTOMER_ID"));

            Order order = Order
                    .builder()
                    .orderId(resultSet.getInt("ORDER_ID"))
                    .products(items)
                    .totalPrice(resultSet.getInt("TOTAL_PRICE"))
                    .status(OrderStatus.valueOf(resultSet.getString("STATUS")))
                    .customer(customer)
                    .build();

            orderList.add(order);
        }

        return orderList;
    }

    @Override
    public Order findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM ORDER_TBL WHERE ORDER_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Order order = null;
        if (resultSet.next()) {
            String itemStrings = resultSet.getString("ITEMS");
            List<Item> items = gson.fromJson(itemStrings, new TypeToken<List<Item>>() {
            }.getType());
            Customer customer = new Customer();
            customer.setCustomerId(resultSet.getInt("CUSTOMER_ID"));

            order = Order
                    .builder()
                    .orderId(resultSet.getInt("ORDER_ID"))
                    .products(items)
                    .totalPrice(resultSet.getInt("TOTAL_PRICE"))
                    .status(OrderStatus.valueOf(resultSet.getString("STATUS")))
                    .customer(customer)
                    .build();
        }
        return order;
    }

    public List<Order> findByCustomerID(int id) throws Exception {
        List<Order> orderList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM ORDER_TBL WHERE CUSTOMER_ID=? ");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Order order = null;
        if (resultSet.next()) {
            String itemStrings = resultSet.getString("ITEMS");
            List<Item> items = gson.fromJson(itemStrings, new TypeToken<List<Item>>() {
            }.getType());
            Customer customer = new Customer();
            customer.setCustomerId(resultSet.getInt("CUSTOMER_ID"));

            order = Order
                    .builder()
                    .orderId(resultSet.getInt("ORDER_ID"))
                    .products(items)
                    .totalPrice(resultSet.getInt("TOTAL_PRICE"))
                    .status(OrderStatus.valueOf(resultSet.getString("STATUS")))
                    .customer(customer)
                    .build();

            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
