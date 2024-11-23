package model.bl;


import controller.exceptions.order.NoOrderFoundException;
import controller.exceptions.order.DuplicateOrderFoundException;
import controller.exceptions.order.OrderIsEmpty;
import lombok.Getter;
import model.da.OrderDA;
import model.entity.Order;
import model.tools.CRUD;

import java.util.List;

public class OrderBL implements CRUD<Order> {
    @Getter
    private static final OrderBL orderBl = new OrderBL();

    public OrderBL() {
    }

    @Override
    public Order save(Order order) throws Exception {
        try (OrderDA orderDA = new OrderDA()) {
            if (orderDA.findById(order.getOrderId()) == null) {
                if (order.getTotalPrice() != 0) {
                    orderDA.save(order);
                    return order;
                } else {
                    throw new OrderIsEmpty();
                }
            } else {
                throw new DuplicateOrderFoundException();
            }
        }
    }

    @Override
    public Order edit(Order order) throws Exception {
        try (OrderDA OrderDA = new OrderDA()) {
            if (OrderDA.findById(order.getOrderId()) != null) {
                OrderDA.edit(order);
                return order;
            } else {
                throw new NoOrderFoundException();
            }
        }
    }

    @Override
    public Order remove(int id) throws Exception {
        try (OrderDA OrderDA = new OrderDA()) {
            Order order = OrderDA.findById(id);
            if (order != null) {
                OrderDA.remove(id);
                return order;
            } else {
                throw new NoOrderFoundException();
            }
        }
    }

    @Override
    public List<Order> findAll() throws Exception {
        try (OrderDA OrderDA = new OrderDA()) {
            List<Order> orderList = OrderDA.findAll();
            if (!orderList.isEmpty()) {
                return orderList;
            } else {
                throw new NoOrderFoundException();
            }
        }
    }

    @Override
    public Order findById(int id) throws Exception {
        try (OrderDA OrderDA = new OrderDA()) {
            Order order = OrderDA.findById(id);
            if (order != null) {
                return order;
            } else {
                throw new NoOrderFoundException();
            }
        }
    }

    public List<Order> findByCustomerID(int id) throws Exception {
        try (OrderDA OrderDA = new OrderDA()) {
            List<Order> orders = OrderDA.findByCustomerID(id);
            if (orders != null) {
                return orders;
            } else {
                throw new NoOrderFoundException();
            }
        }
    }

}
