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
    private List<Item> products;
    private Integer totalPrice;
    private OrderStatus status;
    private Customer customer;


    public void addItem(Item product) {
        products.add(product);
    }

    public void removeItem(Item product) {
        products.remove(product);
    }

    public void calculateTotalPrice() {
        totalPrice = 0;
        for (Item item : products) {
            totalPrice += item.getPrice();
        }
    }

    public void updateStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
