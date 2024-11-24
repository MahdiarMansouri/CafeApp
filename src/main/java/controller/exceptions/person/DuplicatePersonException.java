package controller.exceptions.person;

public class DuplicatePersonException extends RuntimeException {
    public DuplicatePersonException() {
        super("Duplicate person !!!");
    }
}
