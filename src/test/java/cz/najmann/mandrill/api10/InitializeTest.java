package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.http.HttpComponentsHandler;
import cz.najmann.mandrill.api10.http.JettyHttpHandler;
import cz.najmann.mandrill.api10.json.GsonJsonHandler;
import cz.najmann.mandrill.api10.json.JacksonJsonHandler;
import org.junit.Ignore;
import org.junit.Test;

public class InitializeTest {

    @Test(expected = NullPointerException.class)
    public void testGsonJsonHandler() {
        new GsonJsonHandler(null);
    }

    @Test(expected = NullPointerException.class)
    public void testJacksonJsonHandler() {
        new JacksonJsonHandler(null);
    }

    @Test(expected = NullPointerException.class)
    public void testApacheHttpComponents() {
        new HttpComponentsHandler(null);
    }

    @Ignore
    @Test(expected = NullPointerException.class)
    public void testJettyHttpHandler() {
        new JettyHttpHandler(null);
    }
}
