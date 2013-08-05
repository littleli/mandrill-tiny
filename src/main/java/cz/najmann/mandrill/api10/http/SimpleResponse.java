package cz.najmann.mandrill.api10.http;

/**
 * Very minimal abstraction of http response
 */
public interface SimpleResponse {

    int getStatus();

    String getContent();
}
