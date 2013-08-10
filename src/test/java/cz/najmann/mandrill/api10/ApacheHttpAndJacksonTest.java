package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.http.HttpComponentsHandler;
import cz.najmann.mandrill.api10.json.JacksonJsonHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

public class ApacheHttpAndJacksonTest extends BasicTemplateTest {

    @Override
    public void init() {
        HttpClient httpClient = new DefaultHttpClient();
        httpHandler = new HttpComponentsHandler(httpClient);
        jsonHandler = new JacksonJsonHandler();
        initServiceFactory();
    }

    @Test(expected = MandrillError.class)
    public void testFailedConnection() {
        httpHandler.doPost("none", "Hello world!");
    }
}
