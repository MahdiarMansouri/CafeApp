package controller.exceptions.user;

public class DuplicateUsernameException extends Exception {
    public DuplicateUsernameException() {
        super("Duplicate Username !!!");
    }
}
