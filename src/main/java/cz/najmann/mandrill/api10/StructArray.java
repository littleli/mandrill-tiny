package cz.najmann.mandrill.api10;

import java.util.ArrayList;

public final class StructArray extends ArrayList<Struct> {

    public static StructArray with(Struct struct) {
        return (new StructArray()).append(struct);
    }

    public StructArray append(Struct struct) {
        add(struct);
        return this;
    }
}
