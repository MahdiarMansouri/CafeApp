package model.entity;


import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import model.entity.enums.Category;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder


public class Item  {
    private int itemId;
    private String name;
    private String description;
    private int price;
    private Category category;
    private boolean isAvailable;



    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
