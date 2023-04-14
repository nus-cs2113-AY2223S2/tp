package seedu.duke.util;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Constants {
    // DATA FILE NAME USED FOR APPLIATION. default: data.json
    public static final String FILE_NAME = "data.json";

    // ACCEPTABLE DATE FORMAT FOR DEPOSIT AND EXPENSE. default: dd-MM-uuuu
    public static final String ACCEPTABLE_DATE_FORMAT_STRING = "dd-MM-uuuu";
    public static final DateTimeFormatter ACCEPTABLE_DATE_FORMAT = DateTimeFormatter.ofPattern
        (ACCEPTABLE_DATE_FORMAT_STRING).withResolverStyle(ResolverStyle.STRICT);

    // OUTPUT DATE FORMAT FOR DISPLAYING DATE. default: dd MMM uuuu
    public static final String OUTPUT_DATE_FORMAT_STRING = "dd MMM uuuu";
    public static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT_STRING);

    // MAX LENGTH FOR ALL STRING
    public static final int STRING_MAX_LENGTH = 30;

    // MAX LIMIT FOR NUMBER OF BUDGETS
    public static final int MAX_NO_OF_BUDGETS = 15;

    // COLOUR CODES
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";

    // BARS
    public static final int MAX_BARS = 20;
    public static final String BLACK_BAR = "\u2588";
    public static final String WHITE_BAR = "\u2591";
    public static final int YELLOW_BAR_MIN_COUNT = 15;
    public static final int RED_BAR_MIN_COUNT = 18;
}
