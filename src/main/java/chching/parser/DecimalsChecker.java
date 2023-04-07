package chching.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecimalsChecker {

    /**
     * Checks that input value satisfies the 2 decimal place requirement
     * @param valueString string of value field
     * @return true if requirements is satisfied, false otherwise
     */
    public static boolean isTwoDecimals(String valueString) {
        Pattern pattern = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
        Matcher matcher = pattern.matcher(valueString);
        boolean isMatch = matcher.matches();
        return isMatch;

    }
}
