package serialization;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

interface Serializer {
    void serialize(Album ob, File file) throws IOException;
    Object deserialize(File file) throws IOException;

}
