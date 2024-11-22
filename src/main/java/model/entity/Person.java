package model.entity;


import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import model.entity.enums.Gender;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder


public class Person {
    private int id;
    private String name;
    private String family;
    private Gender gender;
    private String nationalId;
    private String phoneNumber;
    private User user;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
