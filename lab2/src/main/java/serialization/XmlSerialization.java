package serialization;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class XmlSerialization implements Serializer {
    private final XmlMapper xmlMapper;
    public XmlSerialization() {
        this.xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
    }
    @Override
    public void serialize(Album ob,File file) throws IOException {
        try {
            xmlMapper.writeValue(file, ob);

        } catch (JsonGenerationException | JsonMappingException e) {
            throw new IOException("Error occurred during serialization", e);
        }
    }


    @Override
    public Object deserialize(File file) throws IOException {
        return xmlMapper.readValue(file, Album.class);
    }
}
