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


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
