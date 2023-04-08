package pocketpal.data.parsing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import pocketpal.data.entrylog.EntryLog;

import java.time.LocalDateTime;

public class EntryLogParser {
    private static final GsonBuilder GSON_BUILDER = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
    private static final Gson GSON = GSON_BUILDER.create();

    /**
     * Converts the given EntryLog instance into JSON format
     * @param entryLog EntryLog to be serialised
     * @return GSON-serialised JSON string
     */
    public static String serialise(EntryLog entryLog) {
        return GSON.toJson(entryLog);
    }

    /**
     * Converts the given JSON string into an EntryLog instance
     * @param json GSON-serialised JSON string
     * @return EntryLog corresponding to JSON
     */
    public static EntryLog deserialise(String json) {
        return GSON.fromJson(json, new TypeToken<EntryLog>(){}.getType());
    }
}
