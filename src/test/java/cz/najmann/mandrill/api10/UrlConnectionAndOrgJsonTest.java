package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.http.URLConnectionHandler;
import cz.najmann.mandrill.api10.json.OrgJsonHandler;

public class UrlConnectionAndOrgJsonTest extends BasicTemplateTest {

    @Override
    public void init() throws Exception {
        this.httpHandler = new URLConnectionHandler();
        this.jsonHandler = new OrgJsonHandler();
    }
}
