package seedu.apollo.module;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * A class representing a timetable for the week.
 */
public class Timetable {
    public static DateTimeFormatter parsePattern = DateTimeFormatter.ofPattern("HHmm", Locale.ENGLISH);
    public static DateTimeFormatter printPattern = DateTimeFormatter.ofPattern("hh:mma", Locale.ENGLISH);
    private String classNumber;
    private String lessonType;
    private String day;
    private String startTime;
    private String endTime;

    /**
     * Gets the class number.
     *
     * @return The class number.
     */
    public String getClassNumber() {
        return classNumber;
    }

    /**
     * Sets the class number.
     *
     * @param classNumber The class number to set.
     */
    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    /**
     * Gets the type of lesson.
     *
     * @return The type of lesson.
     */
    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return lessonType + ": " + getStartTime() + "-" + getEndTime();
    }
}
