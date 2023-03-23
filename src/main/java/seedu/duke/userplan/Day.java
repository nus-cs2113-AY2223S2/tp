package seedu.duke.userplan;

//@@author Khulon
public class Day {
    /**
     * Method to convert integer to string according to the day of the week
     *
     * @param day the day that the event starts from
     * @return the string that was converted from an integer
     */
    public static String intToDay(int day) {
        switch (day) {
        case 0:
            return "MONDAY";
        case 1:
            return "TUESDAY";
        case 2:
            return "WEDNESDAY";
        case 3:
            return "THURSDAY";
        case 4:
            return "FRIDAY";
        case 5:
            return "SATURDAY";
        case 6:
            return "SUNDAY";
        default:
            return null;
        }
    }

    /**
     * Method to convert string to integer according to the day of the week
     *
     * @param day the day that the event starts from
     * @return the integer that was converted from a string
     */
    public static int dayToInt(String day) {
        switch (day) {
        case "monday":
            return 0;
        case "tuesday":
            return 1;
        case "wednesday":
            return 2;
        case "thursday":
            return 3;
        case "friday":
            return 4;
        case "saturday":
            return 5;
        case "sunday":
            return 6;
        default:
            return -1;
        }
    }
}
