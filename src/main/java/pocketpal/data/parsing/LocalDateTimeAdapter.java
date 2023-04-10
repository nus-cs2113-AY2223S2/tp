package pocketpal.data.parsing;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Adapter for Gson to (de)serialize LocalDateTime
 */
public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
    @Override
    public LocalDateTime read(JsonReader reader) throws IOException {
        String savedDateTime = reader.nextString();
        try {
            return LocalDateTime.parse(savedDateTime);
        } catch (DateTimeParseException e) {
            throw new IOException();
        }
    }

    @Override
    public void write(JsonWriter writer, LocalDateTime localDateTime) throws IOException {
        writer.value(String.valueOf(localDateTime));
    }
}
