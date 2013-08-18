package cz.najmann.mandrill.api10;

/**
 * Represents the lower-level error holding class.
 */
public class MandrillError extends RuntimeException {

    public MandrillError(String message) {
        super(message);
    }

    public MandrillError(Throwable cause) {
        super(cause);
    }
}
