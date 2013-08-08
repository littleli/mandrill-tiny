package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.json.JsonHandler;
import cz.najmann.mandrill.api10.json.OrgJsonHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class OrgJsonTest {

    JsonHandler jsonHandler = new OrgJsonHandler();

    String trivial;
    String error;
    String arrayResponse;

    String loadFile(String fn) throws IOException {
        InputStream is = null;
        try {
            is = OrgJsonTest.class.getResourceAsStream(fn);
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
        // parsing
        Struct e = jsonHandler.fromJson(error, Struct.class);
        assertEquals(e.getValue("status", String.class), "error");

        long l = e.getValue("code", Number.class).longValue();
        assertEquals(l, -1);

        assertEquals(e.getValue("name", String.class), "Invalid_Key");

        assertEquals(e.getValue("message", String.class), "Invalid API key");
    }

    @Test
    public void testNormalInput() {
        StructArray ary = jsonHandler.fromJson(arrayResponse, StructArray.class);

        System.out.println(ary.toString());
    }
}
