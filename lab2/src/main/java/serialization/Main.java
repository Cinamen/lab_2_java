package serialization;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        Album album = new Album.Builder("Preacher's Daughter")
                .dateOfRelease(LocalDate.of(2022, 5, 12))
                .totalLength(Duration.ofMinutes(75))
                .totalSoldUnits(50000)
                .build();


        Serializer xmlSerializer = new XmlSerialization();
        File xmlFile = new File("album.xml");
        try {
            xmlSerializer.serialize(album, xmlFile);
            System.out.println("Written to XML file.");

            Album deserializedAlbum = (Album) xmlSerializer.deserialize(xmlFile);
            System.out.println("Read Album from XML: " + deserializedAlbum);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Serializer jsonSerializer = new JsonSerialization();
        File jsonFile = new File("album.json");
        try {
            jsonSerializer.serialize(album, jsonFile);
            System.out.println("Written to JSON file.");

            Album deserializedAlbum = (Album) jsonSerializer.deserialize(jsonFile);
            System.out.println("Read Album from JSON: " + deserializedAlbum);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Serializer txtSerializer = new TxtSerialization();
        File txtFile = new File("album.txt");
        try {
            txtSerializer.serialize(album, txtFile);
            System.out.println("Written to TXT file.");

            Album deserializedAlbum = (Album) txtSerializer.deserialize(txtFile);
            System.out.println("Read Album from TXT: " + deserializedAlbum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
