package cz.najmann.mandrill.api10;

import java.util.LinkedList;

/**
 * User: littleli
 * Date: 3.8.13
 * Time: 0:43
 */
public final class StructArray extends LinkedList<Struct> {

    public static StructArray with(Struct struct) {
        return (new StructArray()).append(struct);
    }

    public StructArray append(Struct struct) {
        add(struct);
        return this;
    }
}
