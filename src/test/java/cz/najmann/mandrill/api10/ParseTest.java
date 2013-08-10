package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.json.JsonHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class ParseTest {

    protected JsonHandler jsonHandler;

    String trivial;
    String error;
    String arrayResponse;

    public String loadFile(String fn) throws IOException {
        InputStream is = null;
        try {
            is = ParseTest.class.getResourceAsStream(fn);
            StringWriter writer = new StringWriter();
            int b;
            while ((b = is.read()) != -1)
                writer.write(b);
            return writer.toString();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    @Before
    public void loadFiles() throws IOException {
        trivial = loadFile("/trivial_response.json");
        error = loadFile("/error_response.json");
        arrayResponse = loadFile("/array_response.json");
    }

    @Test
    public void testParsingTrivialInput() {
        Struct t = jsonHandler.fromJson(trivial, Struct.class);
        assertEquals(t.getValue("key", String.class), "example key");
    }

    @Test
    public void testErrorInput() {
        Struct e = jsonHandler.fromJson(error, Struct.class);
        assertEquals(e.getValue("status", String.class), "error");

        long code = e.getValue("code", Number.class).longValue();
        assertEquals(code, -1);

        assertEquals(e.getValue("name", String.class), "Invalid_Key");

        assertEquals(e.getValue("message", String.class), "Invalid API key");
    }

    @Test
    public void testNormalInput() {
        StructArray ary = jsonHandler.fromJson(arrayResponse, StructArray.class);
        assertNotNull(ary);
        assertEquals(1, ary.size());
        assertEquals("2013-01-01 15:30:27", ary.get(0).get("created_at"));
        assertEquals(42, ary.get(0).getValue("clicks", Number.class).longValue());
    }
}
