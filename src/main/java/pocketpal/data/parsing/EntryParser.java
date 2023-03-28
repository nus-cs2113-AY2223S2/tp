package pocketpal.data.parsing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pocketpal.data.entry.Entry;

import java.time.LocalDateTime;

public class EntryParser {
    private static final GsonBuilder GSON_BUILDER = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
    private static final Gson GSON = GSON_BUILDER.create();

    public static String serialise(Entry entry) {
        return GSON.toJson(entry);
    }

    public static Entry deserialise(String json) {
        return GSON.fromJson(json, Entry.class);
    }
}
