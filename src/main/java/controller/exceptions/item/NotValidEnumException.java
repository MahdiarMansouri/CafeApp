package controller.exceptions.item;

public class NotValidEnumException extends Exception {
    public NotValidEnumException() {
        super("Not a valid enum value !!!");
    }
}
