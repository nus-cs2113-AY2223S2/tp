package seedu.pocketpal.data.parsing;

import com.google.gson.Gson;
import seedu.pocketpal.data.entry.Entry;

public class EntryParser {
    private static final Gson gson = new Gson();
    public static String serialise(Entry entry) {
        return gson.toJson(entry);
    }
    public static Entry deserialise(String json) {
        return gson.fromJson(json, Entry.class);
    }
}
