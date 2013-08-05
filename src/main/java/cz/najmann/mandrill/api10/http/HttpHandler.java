package cz.najmann.mandrill.api10.http;

public interface HttpHandler {

    SimpleResponse doPost(String uri, String content);
}
