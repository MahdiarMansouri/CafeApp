package model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder


public class OrderItem extends Item {
    private int count;
    private int totalPrice;

    private OrderItem(int count, int totalPrice) {
        this.setTotalPrice(this.getCount() * this.getPrice());
    }

}