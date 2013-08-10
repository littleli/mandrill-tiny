package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.http.URLConnectionHandler;
import cz.najmann.mandrill.api10.json.GsonJsonHandler;

public class UrlConnectionAndGsonTest extends BasicTemplateTest {

    @Override
    public void init() throws Exception {
        this.httpHandler = new URLConnectionHandler();
        this.jsonHandler = new GsonJsonHandler();
    }
}
