package pocketpal.backend.constants;

import java.util.regex.Pattern;

public final class MiscellaneousConstants {

    public static final String GENERAL_STORAGE_ERROR_MESSAGE = "Error reading data from line: ";
    public static final String INVALID_AMOUNT_ERROR_MESSAGE = "Amount is not valid for line: ";
    public static final String INVALID_CATEGORY_ERROR_MESSAGE = "Category is not valid for line: ";
    public static final String INVALID_DATE_ERROR_MESSAGE = "Date is not valid for line: ";

    public static final String DATETIME_PATTERN = "d MMM uuuu; HH:mm";
    public static final String METHOD_NOT_IMPLEMENTED = "Unexpected request. This method not implemented.";
    public static final Pattern PATTERN_AMOUNT_2DP = Pattern
            .compile("^(\\d{0,9}.(\\d{1,2}0*))$|^(\\d{1,9}(.\\d{0,2}0*)?)$");
    public static final double AMOUNT_MAX_DOUBLE = 999999999.99;
    public static final double AMOUNT_MIN_DOUBLE = 0.01;
    public static final String AMOUNT_MAX_STRING = "999999999.99";
    public static final String AMOUNT_MIN_STRING = "0.01";
    private MiscellaneousConstants(){
    }
}
