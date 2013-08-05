package cz.najmann.mandrill.api10;

/**
 * User: littleli
 * Date: 1.8.13
 * Time: 23:21
 */
public final class Error {

    private String error;

    private Long code;

    private String name;

    private String message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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
