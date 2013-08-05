package cz.najmann.mandrill.api10;

/**
 * Represents higher-level exception, it holds the deserialized Error instance, which is accessible
 * by <code>getError()</code>. It also holds the http status code accessible by <code>getStatus()</code>
 */
public class MandrillServiceError extends MandrillError {

    private final int status;
    private final Error error;

    public MandrillServiceError(int status, Error error) {
        super(error.getMessage());
        this.status = status;
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }
}
