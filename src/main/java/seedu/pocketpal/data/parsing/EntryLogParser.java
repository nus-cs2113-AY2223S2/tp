package seedu.pocketpal.data.parsing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import seedu.pocketpal.data.entrylog.EntryLog;

import java.time.LocalDateTime;

public class EntryLogParser {
    private static final GsonBuilder GSON_BUILDER = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
    private static final Gson GSON = GSON_BUILDER.create();
    public static String serialise(EntryLog entryLog) {
        return GSON.toJson(entryLog);
    }
    public static EntryLog deserialise(String json) {
        return GSON.fromJson(json, new TypeToken<EntryLog>(){}.getType());
    }
}
