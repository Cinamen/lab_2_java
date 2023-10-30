package serialization;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class JsonSerialization implements Serializer {
    private final JsonMapper jsonMapper;
    public JsonSerialization() {
        this.jsonMapper = new JsonMapper();
        jsonMapper.registerModule(new JavaTimeModule());
    }
    @Override
    public void serialize(Album ob,File file) throws IOException {
        try {
            jsonMapper.writeValue(file, ob);

        } catch (JsonGenerationException | JsonMappingException e) {
            throw new IOException("Error occurred during serialization", e);
        }
    }


    @Override
    public Object deserialize(File file) throws IOException {
        return jsonMapper.readValue(file, Album.class);
    }
}
