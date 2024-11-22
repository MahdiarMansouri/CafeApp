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


public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Customer(String firstName, String lastName, String phoneNumber) {}

    public void placeOrder(Order order){

    }

    public void viewMenu(Menu menu){
        menu.viewMenu();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
