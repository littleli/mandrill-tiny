package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.http.HttpHandler;
import cz.najmann.mandrill.api10.http.JettyHttpHandler;
import cz.najmann.mandrill.api10.json.JacksonJsonHandler;
import cz.najmann.mandrill.api10.json.JsonHandler;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class requires running on Java 7 due to dependencies on Jetty 9
 */
public class JettyHttpAndJacksonTest {

    static int version;

    @BeforeClass
    public static void initBefore() {
        version = Integer.valueOf(System.getProperty("java.version").replace(".", "").substring(0, 2));
    }

    Object httpc;
    MandrillServiceFactory serviceFactory;
    JsonHandler jsonHandler;
    HttpHandler httpHandler;

    @Before
    public void init() throws Exception {
        if (version >= 17) {
            httpc = new HttpClient(new SslContextFactory(false));
            ((HttpClient) httpc).start();
            this.jsonHandler = new JacksonJsonHandler();
            this.httpHandler = new JettyHttpHandler((HttpClient) httpc);
            serviceFactory = new MandrillServiceFactory(Constants.TEST_API_KEY, jsonHandler, httpHandler);
        }
    }

    @Test
    public void testFailedConnection() {
        if (version >= 17) {
            try {
                httpHandler.doPost("none", "Hello world!");
                assertTrue(false);
            } catch (MandrillError e) {
                assertNotNull(e);
            }
        }
    }

    @After
    public void close() throws Exception {
        if (version >= 17) {
            ((HttpClient) httpc).stop();
        }
    }

    @Test
    public void usersCallsTest() {
        if (version >= 17) {
            Category.Users users = serviceFactory.getService(Category.Users.class);

            assertEquals("PONG!", users.ping());
            assertEquals("PONG!", users.ping2().getValue("PING", String.class));

            assertNotNull(users.info());
            assertNotNull(users.senders());
        }
    }

    @Test
    public void messagesCallsTest() {
        if (version >= 17) {
            Category.Messages messages = serviceFactory.getService(Category.Messages.class);

            Struct message = Struct.with("html", "<b>Hello hello, this is in bold</b>")
                    .set("to", StructArray.with(Struct.with("email", "dummy@test.mandrillapp.com")))
                    .set("subject", "Just a junit test email")
                    .set("from_email", "api.test@gmail.com")
                    .set("from_name", "BigTester");

            StructArray handle = messages.send(message, false, null, null);
            assertNotNull(handle);
        }
    }
}
