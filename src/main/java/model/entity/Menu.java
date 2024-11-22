package model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Menu {
    private int menuId;
    private List<Item> menuProducts;

    public void addItem(Item product) {
        menuProducts.add(product);
    }

    public void removeItem(Item product) {
        menuProducts.remove(product);
    }

    public void viewMenu() {
        Gson gson = new Gson();
        String json = gson.toJson(menuProducts);
        System.out.println(json);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}


