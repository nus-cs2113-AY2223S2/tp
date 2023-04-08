package seedu.constants;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.Locale;

public class DateConstants {
    public static final String PARSE_FORMAT = "d/M/yyyy";
    public static final String DATABASE_FORMAT = "d/M/yyyy";
    public static final DateTimeFormatter PARSE_DTF = 
            DateTimeFormatter.ofPattern(PARSE_FORMAT, Locale.ENGLISH);
    public static final DateTimeFormatter DATABASE_DTF = 
            DateTimeFormatter.ofPattern(DATABASE_FORMAT, Locale.ENGLISH);
}
