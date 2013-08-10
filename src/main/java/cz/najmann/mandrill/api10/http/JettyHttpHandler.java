package cz.najmann.mandrill.api10.http;

import cz.najmann.mandrill.api10.MandrillError;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;

public final class JettyHttpHandler implements HttpHandler {

    private final HttpClient httpClient;

    public JettyHttpHandler(HttpClient httpClient) {
        if (httpClient == null) {
            throw new NullPointerException("Instance needed");
        }
        this.httpClient = httpClient;
    }

    @Override
    public SimpleResponse doPost(String uri, String content) {
        final ContentResponse response;
        try {
            response = httpClient.newRequest(uri)
                    .content(new StringContentProvider(content), "application/json")
                    .send();
            return new SimpleResponse(response.getStatus(), response.getContentAsString());
        } catch (Exception e) {
            throw new MandrillError(e);
        }
    }
}
