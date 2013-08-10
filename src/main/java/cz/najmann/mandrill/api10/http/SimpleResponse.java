package cz.najmann.mandrill.api10.http;

/**
 * Very minimal abstraction of http response
 */
public class SimpleResponse {

    private final int status;
    private final String content;

    public SimpleResponse(int status, String content) {
        this.status = status;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }
}
