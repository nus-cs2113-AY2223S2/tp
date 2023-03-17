package seedu.pocketpal.data.parsing;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.pocketpal.data.entry.Entry;
import seedu.pocketpal.data.entrylog.EntryLog;

import java.util.List;

public class EntryLogParser {
    private static final Gson gson = new Gson();
    public static String serialise(EntryLog entryLog) {
        return gson.toJson(entryLog);
    }
    public static EntryLog deserialise(String json) {
        List<Entry> entries = gson.fromJson(json, new TypeToken<List<Entry>>(){}.getType());
        return new EntryLog(entries);
    }
}
