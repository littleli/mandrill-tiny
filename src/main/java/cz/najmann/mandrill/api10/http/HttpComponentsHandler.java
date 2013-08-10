package cz.najmann.mandrill.api10.http;

import cz.najmann.mandrill.api10.MandrillError;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.io.InputStream;

import static cz.najmann.mandrill.api10.http.HttpHandlerHelp.close;
import static cz.najmann.mandrill.api10.http.HttpHandlerHelp.readResponse;

public final class HttpComponentsHandler implements HttpHandler {

    private final HttpClient httpClient;

    public HttpComponentsHandler(HttpClient httpClient) {
        if (httpClient == null) {
            throw new NullPointerException("HttpClient instance required");
        }
        this.httpClient = httpClient;
    }

    @Override
    public SimpleResponse doPost(String uri, String content) {
        HttpPost post = new HttpPost(uri);
        post.addHeader("Content-Type", "application/json");
        InputStream inputStream = null;
        try {
            post.setEntity(new StringEntity(content, "UTF-8"));
            HttpResponse response = httpClient.execute(post);
            StatusLine statusLine = response.getStatusLine();
            inputStream = response.getEntity().getContent();
            return new SimpleResponse(statusLine.getStatusCode(), new String(readResponse(inputStream)));
        } catch (IOException e) {
            throw new MandrillError(e);
        } finally {
            close(inputStream);
        }
    }
}
