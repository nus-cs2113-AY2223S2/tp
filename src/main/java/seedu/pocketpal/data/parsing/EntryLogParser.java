package seedu.pocketpal.data.parsing;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.pocketpal.data.entrylog.EntryLog;

public class EntryLogParser {
    private static final Gson gson = new Gson();
    public static String serialise(EntryLog entryLog) {
        return gson.toJson(entryLog);
    }
    public static EntryLog deserialise(String json) {
        return gson.fromJson(json, new TypeToken<EntryLog>(){}.getType());
    }
}
