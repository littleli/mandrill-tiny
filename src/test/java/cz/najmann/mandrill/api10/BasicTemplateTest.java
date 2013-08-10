package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.http.HttpHandler;
import cz.najmann.mandrill.api10.json.JsonHandler;
import org.junit.Before;
import org.junit.Test;

import static cz.najmann.mandrill.api10.Category.Messages;
import static cz.najmann.mandrill.api10.Category.Users;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class BasicTemplateTest {

    JsonHandler jsonHandler;
    HttpHandler httpHandler;
    MandrillServiceFactory serviceFactory;

    @Before
    public abstract void init() throws Exception;

    @Before
    public void initServiceFactory() {
        serviceFactory = new MandrillServiceFactory(Constants.TEST_API_KEY, jsonHandler, httpHandler);
    }

    @Test(expected = IllegalArgumentException.class)
    public void badInitTest() {
        serviceFactory.getService(Integer.class);
    }

    @Test
    public void usersCallsTest() {
        Users users = serviceFactory.getService(Users.class);

        assertEquals("PONG!", users.ping());
        assertEquals("PONG!", users.ping2().getValue("PING", String.class));

        assertNotNull(users.info());
        assertNotNull(users.senders());
    }

    @Test
    public void messagesCallsTest() {
        Messages messages = serviceFactory.getService(Messages.class);

        Struct message = Struct.with("html", "<b>Hello hello, this is in bold</b>")
                .set("to", StructArray.with(Struct.with("email", "dummy@test.mandrillapp.com")))
                .set("subject", "Just a junit test email")
                .set("from_email", "api.test@gmail.com")
                .set("from_name", "BigTester");

        StructArray handle = messages.send(message, false, null, null);
        assertNotNull(handle);
    }
}
