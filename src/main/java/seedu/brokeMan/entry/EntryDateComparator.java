package seedu.brokeMan.entry;

import java.util.Comparator;

public class EntryDateComparator implements Comparator<Entry> {
    /**
     * Compares two entries in terms of their time
     *
     * @param e1 the first entry to be compared.
     * @param e2 the second entry to be compared.
     * @return integer value that shows which of the two entries is more recent in time
     */
    public int compare(Entry e1, Entry e2) {
        return e2.time.compareTo(e1.time);
    }
}
