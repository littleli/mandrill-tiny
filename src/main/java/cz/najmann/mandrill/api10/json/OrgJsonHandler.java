package cz.najmann.mandrill.api10.json;

import cz.najmann.mandrill.api10.Struct;
import cz.najmann.mandrill.api10.StructArray;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class OrgJsonHandler implements JsonHandler {

    @Override
    public <T> T fromJson(String json, Class<T> t) {
        JSONTokener tokener = new JSONTokener(json);
        try {
            if (tokener.more()) {
                Object o = tokener.nextValue();
                if (t == null || t == Void.class) {
                    return null;
                }
                // is exactly the type wanted
                // will likely work for String, Boolean, Integer, Long
                // Double
                if (t.isAssignableFrom(o.getClass())) {
                    return t.cast(o);
                } else if (t.isAssignableFrom(Struct.class) || t.isAssignableFrom(StructArray.class)) {
                    return t.cast(parse(o));
                }
            }
            throw new IllegalStateException("Unexpected input type");
        } catch (Exception e) {
            throw new JsonError(e);
        }
    }

    private Object parse(Object tokValue) throws JSONException {
        if (tokValue instanceof JSONArray) {
            return parse(new StructArray(), (JSONArray) tokValue);
        } else if (tokValue instanceof JSONObject) {
            return parse(new Struct(), (JSONObject) tokValue);
        } else {
            return tokValue;
        }
    }

    private Struct parse(Struct s, JSONObject o) throws JSONException {
        Iterator i = o.keys();
        while (i.hasNext()) {
            String key = (String) i.next();
            s.put(key, parse(o.get(key)));
        }
        return s;
    }

    private StructArray parse(StructArray arry, JSONArray a) throws JSONException {
        final int len = a.length();
        for (int i = 0; i < len; i++) {
            arry.add((Struct) parse(a.get(i)));
        }
        return arry;
    }

    @Override
    public String toJson(Object o) {
        try {
            if (o == null) {
                throw new NullPointerException();
            } else if (o instanceof Collection) {
                JSONArray array = new JSONArray((Collection) o);
                return array.toString();
            } else if (o instanceof Map) {
                JSONObject obj = new JSONObject((Map) o);
                return obj.toString();
            } else if (o instanceof Double || o instanceof Float) {
                return String.valueOf(((Number) o).doubleValue());
            } else if (o instanceof Number) {
                return String.valueOf(((Number) o).longValue());
            } else if (o instanceof String) {
                return (String) o;
            }
            throw new IllegalArgumentException("Type " + o.getClass().getSimpleName() + " not supported");
        } catch (Exception e) {
            throw new JsonError(e);
        }
    }
}
