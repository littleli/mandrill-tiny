package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.http.JettyHttpHandler;
import cz.najmann.mandrill.api10.json.JacksonJsonHandler;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.junit.After;
import org.junit.Test;

public class JettyHttpAndJackson extends BasicTemplateTest {

    HttpClient httpc = new HttpClient(new SslContextFactory(false));

    @Override
    public void init() throws Exception {
        httpc.start();
        this.jsonHandler = new JacksonJsonHandler();
        this.httpHandler = new JettyHttpHandler(httpc);
        initServiceFactory();
    }

    @Test(expected = MandrillError.class)
    public void testFailedConnection() {
        httpHandler.doPost("none", "Hello world!");
    }

    @After
    public void close() throws Exception {
        httpc.stop();
    }
}
