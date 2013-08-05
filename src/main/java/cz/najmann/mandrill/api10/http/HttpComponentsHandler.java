package cz.najmann.mandrill.api10.http;

import cz.najmann.mandrill.api10.MandrillError;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.io.InputStream;

import static cz.najmann.mandrill.api10.http.HttpHandlerHelp.readResponse;

public final class HttpComponentsHandler implements HttpHandler {

    private final HttpClient httpClient;

    public HttpComponentsHandler(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public SimpleResponse doPost(String uri, String content) {
        HttpPost post = new HttpPost(uri);
        post.addHeader("Content-Type", "application/json");
        try {
            post.setEntity(new StringEntity(content, "UTF-8"));
            HttpResponse response = httpClient.execute(post);
            StatusLine statusLine = response.getStatusLine();

            final int status = statusLine.getStatusCode();

            InputStream inputStream = response.getEntity().getContent();
            final String incoming = new String(readResponse(inputStream));

            return new SimpleResponse() {
                @Override
                public int getStatus() {
                    return status;
                }

                @Override
                public String getContent() {
                    return incoming;
                }
            };
        } catch (IOException e) {
            throw new MandrillError(e);
        }
    }
}
