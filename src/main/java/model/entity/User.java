package model.entity;


import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import model.entity.enums.Role;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)


public class User {
    private int user_id;
    private String username;
    private String password;
    private Role role;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
