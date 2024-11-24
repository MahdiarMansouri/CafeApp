package model.entity;


import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import model.entity.enums.OrderStatus;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder


public class Order {
    private int orderId;
    private List<OrderItem> products;
    private Integer totalPrice;
    private OrderStatus status;
    private Customer customer;

    public Order(List<OrderItem> products) {
        totalPrice = 0;
        for (OrderItem orderItem : products) {
            totalPrice += orderItem.getTotalPrice();
        }
        this.setOrderId(totalPrice);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
