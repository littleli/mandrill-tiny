package cz.najmann.mandrill.api10;

import java.util.LinkedHashMap;

public final class Struct extends LinkedHashMap<String, Object> {

    public Struct set(String name, Object o) {
        this.put(name, o);
        return this;
    }

    public <T> T getValue(String name, Class<T> type) {
        return type.cast(super.get(name));
    }

    public static Struct with(String key, Object val) {
        return (new Struct()).set(key, val);
    }
}
