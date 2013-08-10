package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.http.JettyHttpHandler;
import cz.najmann.mandrill.api10.json.JacksonJsonHandler;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.junit.After;

public class JettyHttpAndJacksonTest extends BasicTemplateTest {

    HttpClient httpc = new HttpClient(new SslContextFactory(false));

    @Override
    public void init() throws Exception {
        httpc.start();
        this.jsonHandler = new JacksonJsonHandler();
        this.httpHandler = new JettyHttpHandler(httpc);
    }

    @After
    public void close() throws Exception {
        httpc.stop();
    }
}
