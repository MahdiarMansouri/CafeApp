package model.tools;

import java.util.regex.Pattern;

public class Validator {
    public static String nameValidator(String name, String message) throws Exception {
        if(Pattern.matches("^[a-zA-Z\\s]{3,30}$", name)){
            return name;
        }else{
            throw new Exception(message);
        }
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
