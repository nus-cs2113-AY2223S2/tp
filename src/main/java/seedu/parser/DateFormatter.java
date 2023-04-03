package seedu.parser;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
    public DateFormatter() {

    }

    public static Date stringToDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }
    public static String dateToString(Date date){
        return dateFormat.format(date);
    }
}

