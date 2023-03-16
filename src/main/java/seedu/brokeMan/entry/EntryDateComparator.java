package seedu.brokeMan.entry;

import java.util.Comparator;

public class EntryDateComparator implements Comparator<Entry> {
    public int compare(Entry e1, Entry e2) {
        return e1.time.compareTo(e2.time);
    }
}
