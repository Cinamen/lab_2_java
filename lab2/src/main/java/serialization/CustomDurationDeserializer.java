package serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.Duration;

public class CustomDurationDeserializer extends JsonDeserializer<Duration> {

    @Override
    public Duration deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken().isNumeric()) {
            long minutes = jsonParser.getLongValue();
            return Duration.ofMinutes(minutes);
        } else if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
            String durationString = jsonParser.getText();
           // try {
                long minutes = Long.parseLong(durationString);
                return Duration.ofMinutes(minutes);
           // } catch (NumberFormatException e) {

            //}
        }
        //return Duration.ZERO;
        throw new IOException("Can't deserialize duration object " + jsonParser);
    }
}
