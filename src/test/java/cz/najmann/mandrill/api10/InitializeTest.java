package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.http.HttpComponentsHandler;
import cz.najmann.mandrill.api10.json.GsonJsonHandler;
import cz.najmann.mandrill.api10.json.JacksonJsonHandler;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class InitializeTest {

    static int version;

    @BeforeClass
    public static void initBefore() {
        version = Integer.valueOf(System.getProperty("java.version").replace(".", "").substring(0, 2));
    }

    @Test(expected = NullPointerException.class)
    public void failedFactoryInit() {
        new MandrillServiceFactory(null, null, null);
    }

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

    @Test
    public void testJettyHttpHandler() {
        if (version >= 17) {
            try {
                new cz.najmann.mandrill.api10.http.JettyHttpHandler(null);
                assertTrue(false);
            } catch (NullPointerException e) {
                assertNotNull(e);
            }
        }
    }
}
