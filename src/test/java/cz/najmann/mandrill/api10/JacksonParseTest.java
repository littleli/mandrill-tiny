package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.json.JacksonJsonHandler;

public class JacksonParseTest extends ParseTest {

    public JacksonParseTest() {
        this.jsonHandler = new JacksonJsonHandler();
    }
}
