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


public class Inventory {
    private int itemId;
    private String itemName;
    private int quantity;
    private double unitPrice;


    public Inventory(int itemId, String itemName, int quantity, double unitPrice) {}

    public void updateStock(int quantity) {
        this.quantity = quantity;
    }

    public void reduceStock(int quantity) {
        this.quantity -= quantity;
    }

    public double getStockValue(int quantity) {
        return (this.unitPrice * quantity);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
