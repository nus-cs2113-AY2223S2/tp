package data;

import com.google.gson.annotations.JsonAdapter;
import utils.GsonLocalDateAdaptor;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Time implements Comparable<Time> {
    // This class is used to deal with the initialization, access, and change of DateTime

    @JsonAdapter(GsonLocalDateAdaptor.class)
    protected LocalDate date;

    public Time(LocalDate date) {
        this.date = date;
    }

    public LocalDate getTime() {
        return this.date;
    }

    public static Integer getCurrentMonth() {
        return LocalDate.now().getMonthValue();
    }

    public static Integer getCurrentYear() {
        return LocalDate.now().getYear();
    }

    public static boolean isFutureYear(String year) {
        return Integer.parseInt(year) > getCurrentYear();
    }

    public static boolean isFutureMonth(String year, String month) throws IllegalArgumentException {
        if (month == null) {
            return isFutureYear(year);
        } else {
            int monthNumber = Month.valueOf(month.toUpperCase()).getValue();
            return isFutureYear(year) | (Integer.parseInt(year) == getCurrentYear()
                    && monthNumber > getCurrentMonth());
        }
    }


    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static Time toTime(String timeString) {
        // The format of the timeString is dd/MM/yyyy
        String standardFormat = timeString.substring(6) + "-"
                + timeString.substring(3, 5) + "-" + timeString.substring(0, 2);
        Time returnTime = new Time(LocalDate.parse(standardFormat));
        return returnTime;
    }

    @Override
    public int compareTo(Time o) {
        return this.getTime().compareTo(o.getTime());
    }
    public String toStringSave() { return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")); }
}
