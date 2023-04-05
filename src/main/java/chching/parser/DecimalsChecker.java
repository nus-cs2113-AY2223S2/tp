package chching.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecimalsChecker {
    public static boolean isTwoDecimals(String valueString) {
        Pattern pattern = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
        Matcher matcher = pattern.matcher(valueString);
        boolean isMatch = matcher.matches();
        return isMatch;

    }
}
