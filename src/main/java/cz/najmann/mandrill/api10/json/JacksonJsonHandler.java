package cz.najmann.mandrill.api10.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * This class is most-likely thread safe. Since JacksonJson is thread safe.
 */
public final class JacksonJsonHandler implements JsonHandler {

    private final ObjectMapper mapper;

    public JacksonJsonHandler() {
        this(new ObjectMapper());
    }

    public JacksonJsonHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T> T fromJson(String json, Class<T> t) {
        try {
            return mapper.readValue(json, t);
        } catch (IOException e) {
            throw new JsonError(e);
        }
    }

    @Override
    public String toJson(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new JsonError(e);
        }
    }
}
