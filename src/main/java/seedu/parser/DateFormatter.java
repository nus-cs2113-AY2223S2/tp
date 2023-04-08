package seedu.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@@author calebcjl
/**
 * This class parses date into a string and vice versa.
 */
public class DateFormatter {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

    public DateFormatter() {
    }

    /**
     * Checks if string is in dd/MM/yy format and converts it into date.
     *
     * @param date String to be checked.
     * @return Corresponding date of string.
     * @throws ParseException If string is not in dd/MM/yy format.
     */
    public static Date stringToDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    /**
     * Converts date to a string in dd/MM/yy format.
     *
     * @param date Date to be converted.
     * @return String of date  in dd/MM/yy format.
     */
    public static String dateToString(Date date){
        return dateFormat.format(date);
    }
}

