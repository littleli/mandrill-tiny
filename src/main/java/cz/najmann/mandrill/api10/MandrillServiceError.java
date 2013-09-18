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

    /**
     * Error class is an error state representing object returned by Mandrill service
     */
    public static final class Error {

        private String status;

        private Long code;

        private String name;

        private String message;

        public String getStatus() {
            return status;
        }

        public void setStatus(String error) {
            this.status = status;
        }

        public Long getCode() {
            return code;
        }

        public void setCode(Long code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
