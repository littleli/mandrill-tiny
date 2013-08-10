package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.http.URLConnectionHandler;
import cz.najmann.mandrill.api10.json.JacksonJsonHandler;

public class UrlConnectionAndJacksonTest extends BasicTemplateTest {

    @Override
    public void init() throws Exception {
        this.jsonHandler = new JacksonJsonHandler();
        this.httpHandler = new URLConnectionHandler();
    }
}
