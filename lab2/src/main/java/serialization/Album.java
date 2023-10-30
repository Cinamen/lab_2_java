package serialization;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

public class Album {
    private String title;
    private LocalDate releaseDate;
    private Duration totalLength;
    private int totalSoldUnits;
    public Album() {

    }
    private Album(Builder builder) {
        this.title = builder.title;
        this.releaseDate = builder.dateOfRelease;
        this.totalLength = builder.totalLength;
        this.totalSoldUnits = builder.totalSoldUnits;;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate getDateOfRelease() {
        return releaseDate;
    }

    public void setDateOfRelease(LocalDate dateOfRelease) {
        this.releaseDate = dateOfRelease;
    }
    @JsonSerialize(using = CustomDurationSerializer.class)
    @JsonDeserialize(using = CustomDurationDeserializer.class)
    public Duration getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(Duration totalLength) {
        this.totalLength = totalLength;
    }

    public int getTotalSoldUnits() {
        return totalSoldUnits;
    }

    public void setTotalSoldUnits(int totalSoldUnits) {
        this.totalSoldUnits = totalSoldUnits;
    }



    public String toString() {
        long minutes = totalLength.toMinutes();
        return "Title of album - " + title
                + "\n Date of release - " + releaseDate
                + "\n Album length - " + minutes + " minutes"
                + "\n Total sold units - " + totalSoldUnits;
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Album album = (Album) object;
        return title.equals(album.title);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public static class Builder {
        private String title;
        private LocalDate dateOfRelease;
        private Duration totalLength;
        private int totalSoldUnits;



        public Builder(String title) {
            this.title = title;
        }


        public Builder dateOfRelease(LocalDate date) {
            this.dateOfRelease = date;
            return this;
        }

        public Builder totalLength(Duration time) {
            this.totalLength = time;
            return this;
        }

        public Builder totalSoldUnits(int amount) {
            this.totalSoldUnits = amount;
            return this;
        }
        public Album build()  {
            return new Album(this);
        }

    }

}
