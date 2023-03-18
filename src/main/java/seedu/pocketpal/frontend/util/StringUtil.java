package seedu.pocketpal.frontend.util;

import java.util.Arrays;

public class StringUtil {
    public static String toTitleCase(String string) {
        return Arrays.stream(string.split(" "))
                     .reduce("", (prevStr, currStr) -> prevStr
                             + currStr.substring(0, 1).toUpperCase()
                             + currStr.substring(1).toLowerCase());
    }
}
