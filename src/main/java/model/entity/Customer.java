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


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
