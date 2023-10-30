package serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;

public class TxtSerialization implements Serializer {
    private final ObjectMapper txtMapper;

    public TxtSerialization() {
        this.txtMapper = new ObjectMapper();
        txtMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void serialize(Album album, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(album.getTitle() + "; "
                    + album.getTotalLength().toMinutes() + "; "
                    + album.getDateOfRelease() + "; "
                    + album.getTotalSoldUnits() + "; ");
        }
    }

    @Override
    public Album deserialize(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        reader.close();

        String[] albumData = line.split("; ");
        String title = albumData[0];
        long totalLengthInMinutes = Long.parseLong(albumData[1]);
        LocalDate dateOfRelease = LocalDate.parse(albumData[2]);
        long totalSoldUnits = Long.parseLong(albumData[3]);

        return new Album.Builder(title)
                .dateOfRelease(dateOfRelease)
                .totalLength(Duration.ofMinutes(totalLengthInMinutes))
                .totalSoldUnits((int) totalSoldUnits)
                .build();
    }
}
