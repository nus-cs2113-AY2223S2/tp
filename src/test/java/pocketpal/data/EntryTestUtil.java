package pocketpal.data;

import pocketpal.backend.BackendTestUtil;
import pocketpal.data.entry.Category;
import pocketpal.data.entry.Entry;

public abstract class EntryTestUtil extends BackendTestUtil {
    protected static final Entry ENTRY_1 = new Entry(
            "5 packets of dried mango",
            5.43,
            Category.FOOD);
    protected static final Entry ENTRY_2 = new Entry(
            "Grab ride to mango farm at 2am",
            16.40,
            Category.TRANSPORTATION);
    protected static final Entry ENTRY_3 = new Entry(
            "Food poisoning",
            99.99,
            Category.MEDICAL);
    protected static final Entry ENTRY_4 = new Entry(
            "Mango juice",
            3.50,
            Category.FOOD);
    protected static final Entry ENTRY_5 = new Entry(
            "Bus ride home",
            1.45,
            Category.TRANSPORTATION);
    protected static final Entry ENTRY_6 = new Entry(
            "Ambulance bill",
            399,
            Category.MEDICAL);

    protected static final Entry ENTRY_7 = new Entry(
            "January salary",
            5600,
            Category.INCOME);

}
