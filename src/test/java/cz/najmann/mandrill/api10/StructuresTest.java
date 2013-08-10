package cz.najmann.mandrill.api10;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StructuresTest {

    @Test
    public void testStaticWithMethod() {
        Struct struct = Struct.with("foo", "bar");
        assertEquals("bar", struct.getValue("foo", String.class));

        struct.set("animal", "parrot").set("age", 2);
        assertEquals("parrot", struct.getValue("animal", String.class));
        int age = struct.getValue("age", Integer.class);
        assertEquals(2, age);

        StructArray array = StructArray.with(struct);
        assertEquals("bar", array.get(0).getValue("foo", String.class));

        array.append(Struct.with("hello", "world"));
        assertTrue(array.get(1).containsKey("hello"));
    }
}
