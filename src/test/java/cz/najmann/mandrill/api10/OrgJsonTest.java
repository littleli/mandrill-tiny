package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.json.OrgJsonHandler;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class OrgJsonTest extends ParseTest {

    public OrgJsonTest() {
        this.jsonHandler = new OrgJsonHandler();
    }

    @Test
    public void testVoid() {
        assertNull(jsonHandler.fromJson("null", Void.class));
        assertNull(jsonHandler.fromJson("null", null));
    }

    @Test
    public void testParseNumber() {
        assertEquals("1", jsonHandler.toJson(1L));
        assertEquals("1.2", jsonHandler.toJson(1.2));
    }

    @Test
    public void testParseString() {
        assertEquals("pat", jsonHandler.toJson("pat"));
    }

    @Test
    public void testCollection() {
        assertEquals("[1,2,3]", jsonHandler.toJson(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void testMap() {
        assertEquals("{\"key\":\"value\"}", jsonHandler.toJson(Collections.singletonMap("key", "value")));
    }
}
