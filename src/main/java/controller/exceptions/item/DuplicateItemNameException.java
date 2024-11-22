package controller.exceptions.item;

public class DuplicateItemNameException extends Exception {
    public DuplicateItemNameException() {
        super("Duplicate item name !!!");
    }
}
