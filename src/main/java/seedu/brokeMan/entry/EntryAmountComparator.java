package seedu.brokeMan.entry;

import seedu.brokeMan.entry.Entry;

import java.util.Comparator;

public class EntryAmountComparator implements Comparator<Entry>{
    public int compare(Entry e1, Entry e2) {
        return (int)((e2.getAmount() - e1.getAmount()) * 100);}
}
