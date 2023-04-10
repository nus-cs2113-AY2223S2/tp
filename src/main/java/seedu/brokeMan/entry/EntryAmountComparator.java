package seedu.brokeMan.entry;


import java.util.Comparator;

public class EntryAmountComparator implements Comparator<Entry>{
    /**
     * Compares two entries in terms of their amount
     *
     * @param e1 the first entry to be compared.
     * @param e2 the second entry to be compared.
     * @return integer value that shows which of the two entries has larger amount
     */
    public int compare(Entry e1, Entry e2) {
        return (int)((e2.getAmount() - e1.getAmount()) * 100);}
}
