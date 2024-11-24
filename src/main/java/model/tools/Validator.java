package model.tools;

import java.util.regex.Pattern;

public class Validator {
    public static boolean nameValidator(String name)  {
        return Pattern.matches("^[a-zA-Z\\s]{3,30}$", name);
    }

    public static boolean phoneNumberValidator(String phoneNumber)  {
        return Pattern.matches("^(\\+989|09)[0-9]{9}$", phoneNumber);
    }

    public static boolean nationalIDValidator(String nationalID)  {
        return Pattern.matches("^[0-9]{10}$", nationalID);
    }

    public static boolean usernameValidator(String username)  {
        return Pattern.matches("^[a-zA-Z0-9_-]{3,}$", username);

    }

    public static boolean passwordValidator(String password)  {
        return Pattern.matches("^(?=(.*[a-zA-Z]){1,})(?=(.*\\d){1,}).{3,}$", password);
    }

    public static boolean itemNameValidator(String itemName)  {
        return Pattern.matches("^[a-zA-Z\\s]{3,}$", itemName);
    }

    public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String value) throws Exception {
        try {
            Enum.valueOf(enumClass, value);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
