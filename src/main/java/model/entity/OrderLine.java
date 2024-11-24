package model.entity;


import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class OrderLine {
    private int id;
    private int count;
    private Item item;
    private Integer totalPrice;

    public void setCount(int count) {
        this.count = count;
        this.totalPrice = calculateTotalPrice();
    }


    private Integer calculateTotalPrice() {
        return this.count * item.getPrice();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
