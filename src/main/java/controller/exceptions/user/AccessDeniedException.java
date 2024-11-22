package controller.exceptions.user;

public class AccessDeniedException extends Exception {
    public AccessDeniedException() {
        super("Wrong Username/Password !!!");
    }
}
