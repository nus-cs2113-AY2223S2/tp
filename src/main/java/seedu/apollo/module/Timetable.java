package seedu.apollo.module;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/**
 * A class representing a timetable for the week.
 */
public class Timetable {
    public static DateTimeFormatter parsePattern = DateTimeFormatter.ofPattern("HHmm", Locale.ENGLISH);
    public static DateTimeFormatter printPattern = DateTimeFormatter.ofPattern("hh:mma", Locale.ENGLISH);

    private static final int NUMBER_OF_WEEKS = 13;
    private String classNumber;
    private String lessonType;
    private String day;
    private String startTime;
    private String endTime;
    private ArrayList<Integer> weeks;

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

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }


    public String getEndTime() {
        return endTime;
    }

    public ArrayList<Integer> getWeeks() {
        return weeks;
    }

    /**
     * This method compresses the weeks of a lesson into a String that can be used for the UI.
     * @param   timetable       The timetable of the lesson.
     * @return  The formatted String representation of the weeks.
     */
    public String compressedWeeks(Timetable timetable) {
        ArrayList<Integer> unfilteredWeeks = timetable.getWeeks();
        ArrayList<Integer> filteredWeeks = new ArrayList<>();
        String formattedWeeks;

        //First, check if the lesson spans the whole 13-week semester
        if (unfilteredWeeks.size() == NUMBER_OF_WEEKS) {
            formattedWeeks = "[Weekly]";
            return formattedWeeks;
        }

        int firstWeek = unfilteredWeeks.get(0);
        int lastWeek = unfilteredWeeks.get(unfilteredWeeks.size() - 1);

        if (lastWeek - firstWeek == unfilteredWeeks.size() - 1) {
            //else if is continguous, then compress
            filteredWeeks.add(firstWeek);
            filteredWeeks.add(lastWeek);
        } else {
            //else print all weeks lesson occurs on
            filteredWeeks = unfilteredWeeks;
        }

        //return String representation
        if (filteredWeeks.size() == unfilteredWeeks.size()){
            formattedWeeks = "["+ "Weeks: " + arrayListToString(filteredWeeks, ", ") + "]";
        } else {
            formattedWeeks = "["+ "Weeks: " + filteredWeeks.get(0) + "-" + filteredWeeks.get(1) + "]";
        }
        return formattedWeeks;
    }

    /**
     * This method converts an ArrayList of Integers into a String.
     * @param arrayList The ArrayList to be converted.
     * @param delimiter The delimiter to separate the elements of the ArrayList.
     * @return String representation of the ArrayList.
     */
    public String arrayListToString(ArrayList<Integer> arrayList, String delimiter){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            stringBuilder.append(arrayList.get(i));
            if (i != arrayList.size() - 1) {
                stringBuilder.append(delimiter);
            }
        }
        return stringBuilder.toString();
    }


    public void setWeeks(ArrayList<Integer> weeks) {
        this.weeks = weeks;
    }

    @Override
    public String toString() {
        return lessonType + ": " + getStartTime() + "-" + getEndTime();
    }
}
