package util.connection.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class JsonUtils {

    private ObjectMapper objectMapper = new ObjectMapper();

    public JsonUtils() {
        objectMapper.registerModule(new Hibernate4Module());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    public String serialize(Object value) throws JsonProcessingException {
        String result = objectMapper.writeValueAsString(value);
        return result;
    }

    public <T> T deserialize(String value, Class<T> t) throws IOException {
        T result = objectMapper.readValue(value, t);
        return result;
    }

    public JsonNode deserialize(String value) throws IOException {
        return objectMapper.readTree(value);
    }

    public ObjectNode deserializeAsObjectNode(String json) throws IOException {
        return (ObjectNode) objectMapper.readTree(json);
    }
}
