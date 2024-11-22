package controller.exceptions.item;

public class NoItemFoundException extends Exception  {
    public NoItemFoundException() {
        super("No item found !!!");
    }
}
