package exception;

public class WrongValueException extends RuntimeException {
    public WrongValueException() {
    }

    public WrongValueException(String message) {
        super(message);
    }
}
