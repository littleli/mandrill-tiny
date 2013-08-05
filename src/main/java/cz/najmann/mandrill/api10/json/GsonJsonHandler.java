package cz.najmann.mandrill.api10.json;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Warning: this class IS NOT thread-safe
 */
public final class GsonJsonHandler implements JsonHandler {

    private Gson gson;

    public GsonJsonHandler() {
        this(new Gson());
    }

    public GsonJsonHandler(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> T fromJson(String json, Class<T> t) {
        try {
            return gson.fromJson(json, t);
        } catch (JsonSyntaxException e) {
            throw new JsonError(e);
        }
    }

    @Override
    public String toJson(Object o) {
        return gson.toJson(o);
    }
}
