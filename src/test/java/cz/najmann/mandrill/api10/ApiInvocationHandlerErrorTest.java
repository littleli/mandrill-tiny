package cz.najmann.mandrill.api10;

import com.google.gson.Gson;
import cz.najmann.mandrill.api10.http.HttpHandler;
import cz.najmann.mandrill.api10.http.SimpleResponse;
import cz.najmann.mandrill.api10.http.URLConnectionHandler;
import cz.najmann.mandrill.api10.json.GsonJsonHandler;
import cz.najmann.mandrill.api10.json.JsonError;
import cz.najmann.mandrill.api10.json.JsonHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApiInvocationHandlerErrorTest {

    String errorContent;

    @Before
    public void init() throws IOException {
        errorContent = ParseTest.loadFile("/error_response.json");
    }

    @Test(expected = MandrillServiceError.class)
    public void testStatusCode() {
        JsonHandler jsonHandler;
        HttpHandler httpHandler;
        MandrillServiceFactory serviceFactory;

        jsonHandler = new GsonJsonHandler();
        httpHandler = new HttpHandler() {
            @Override
            public SimpleResponse doPost(String uri, String content) {
                return new SimpleResponse(400, errorContent);
            }
        };

        serviceFactory = new MandrillServiceFactory(Constants.TEST_API_KEY, jsonHandler, httpHandler);

        Category.Users users = serviceFactory.getService(Category.Users.class);
        try {
            users.ping();
        } catch (MandrillServiceError e) {
            assertEquals("error", e.getError().getStatus());
            assertEquals(400, e.getStatus());
            throw e;
        }
    }

    @Test(expected = MandrillError.class)
    public void testUnexpectedResponse() {
        JsonHandler jsonHandler;
        HttpHandler httpHandler;
        MandrillServiceFactory serviceFactory;

        final Gson gson = new Gson();
        jsonHandler = new JsonHandler() {
            @Override
            public <T> T fromJson(String json, Class<T> t) {
                throw new JsonError(new IllegalArgumentException("Mocked exception"));
            }

            @Override
            public String toJson(Object o) {
                return gson.toJson(o);
            }
        };
        httpHandler = new URLConnectionHandler();

        serviceFactory = new MandrillServiceFactory(Constants.TEST_API_KEY, jsonHandler, httpHandler);

        Category.Users users = serviceFactory.getService(Category.Users.class);
        users.ping();
    }
}
