package pocketpal.data.parsing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pocketpal.data.entry.Entry;

import java.time.LocalDateTime;

public class EntryParser {
    private static final GsonBuilder GSON_BUILDER = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
    private static final Gson GSON = GSON_BUILDER.create();

    /**
     * Converts the given Entry instance into JSON format
     * @param entry Entry to be serialised
     * @return GSON-serialised JSON string
     */
    public static String serialise(Entry entry) {
        return GSON.toJson(entry);
    }

    /**
     * Converts the given JSON string into an Entry instance
     * @param json GSON-serialised JSON string
     * @return Entry corresponding to JSON
     */
    public static Entry deserialise(String json) {
        return GSON.fromJson(json, Entry.class);
    }
}
