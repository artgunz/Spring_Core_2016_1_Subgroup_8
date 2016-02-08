package spring.core.exception;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(final String message) {
        super(message);
    }
}
