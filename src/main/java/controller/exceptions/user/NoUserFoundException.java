package controller.exceptions.user;

public class NoUserFoundException extends Exception {
    public NoUserFoundException() {
        super("No User Found !!!");
    }
}
