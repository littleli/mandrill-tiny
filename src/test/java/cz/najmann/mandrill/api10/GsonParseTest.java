package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.json.GsonJsonHandler;

public class GsonParseTest extends ParseTest {

    public GsonParseTest() {
        this.jsonHandler = new GsonJsonHandler();
    }
}
