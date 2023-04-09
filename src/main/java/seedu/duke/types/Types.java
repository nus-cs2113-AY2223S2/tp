package seedu.duke.types;

public class Types {
    public enum SearchType {
        KEYWORD,
        UPC
    }
    public static final String SESSIONFILEPATH = "./data/InventoryData.csv";
    public static final String ALERTFILEPATH = "./data/AlertData.csv";
    public static final int MAX_QTY = 99999999;
    public enum FileHealth {
        OK,
        CORRUPT,
        MISSING,
        EMPTY
    }
    public enum EditType {
        PRICE_INCREASE,
        PRICE_DECREASE,
        RENAME,
        RECATEGORIZE,
        SOLD,
        BOUGHT
    }
}
