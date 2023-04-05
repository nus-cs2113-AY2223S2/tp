package pocketpal.frontend.util;

import java.util.Arrays;

public class StringUtil {
    /**
     * Format word in each string to have the first letter capitalised,
     * and the rest in lowercase
     * @param string String to be processed
     * @return String in Title Case
     */
    public static String toTitleCase(String string) {
        return Arrays.stream(string.split(" "))
                     .reduce("", (prevStr, currStr) -> prevStr
                             + currStr.substring(0, 1).toUpperCase() // the first letter
                             + currStr.substring(1).toLowerCase()); // the remaining letters
    }

    /**
     * Convert double to string without scientific notation.
     *
     * @param number Double to be converted
     * @return String with up to 5 trailing zero decimal points
     */
    public static String doubleToString(double number) {
        return String.format("%.5f", number);
    }
}
