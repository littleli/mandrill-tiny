package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.http.URLConnectionHandler;
import cz.najmann.mandrill.api10.json.GsonJsonHandler;
import org.junit.Test;

import static org.mockito.Mockito.when;

public class UrlConnectionAndGsonTest extends BasicTemplateTest {

    @Override
    public void init() throws Exception {
        this.httpHandler = new URLConnectionHandler();
        this.jsonHandler = new GsonJsonHandler();
        initServiceFactory();
    }

    @Test(expected = MandrillError.class)
    public void testFailedConnection() {
        httpHandler.doPost("none", "Hello world!");
    }

}
