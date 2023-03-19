package seedu.rainyDay.data;

import seedu.rainyDay.exceptions.ErrorMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * I use this file to test potential changes to parser!
 */
public class Test {
    public static String direction = null;
    public static String description = null;
    public static String category = null;
    public static Double amount = -1.0;

    public static void main(String[] args) {
        String input = "add";
        try {
            addStatement(input);
        } catch (Exception e) {
            System.out.println("error!");
        }
        System.out.println("flow direction is " + direction);
        System.out.println("description is " + description);
        System.out.println("category is " + category);
        System.out.println("price is " + amount);
    }

    private static void addStatement(String userInput) {
        if (userInput.contains("-d") && userInput.contains("-c")) {
            parseDescriptionAndCategory(userInput);
        } else if (userInput.contains("-d")) {
            parseDescriptionOnly(userInput);
        } else if (userInput.contains("-c")) {
            parseCategoryOnly(userInput);
        } else {
            parseOnly(userInput);
        }
    }

    private static void parseDescriptionAndCategory(String userInput) throws IllegalArgumentException {
        if (userInput.contains("-d") && userInput.contains("-c")) {
            Pattern pattern = Pattern.compile("-(in|out)\\s+(?:-d\\s+)?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s+" +
                    "-c\\s+(\\S+(?:\\s+\\S+)*)\\s+\\$([\\d.]+)");
            Matcher matcher = pattern.matcher(userInput);
            Pattern pattern2 = Pattern.compile("-(in|out)\\s+(?:-c\\s+)?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s+" +
                    "-d\\s+(\\S+(?:\\s+\\S+)*)\\s+\\$([\\d.]+)");
            Matcher matcher2 = pattern2.matcher(userInput);
            if (matcher.find()) {
                direction = matcher.group(1);
                description = matcher.group(2);
                category = matcher.group(3);
                amount = Double.parseDouble(matcher.group(4));
            } else if (matcher2.find()) {
                direction = matcher2.group(1);
                category = matcher2.group(2);
                description = matcher2.group(3);
                amount = Double.parseDouble(matcher2.group(4));
            } else {
                throw new IllegalArgumentException(ErrorMessage.WRONG_ADD_FORMAT.toString());
            }
        }
    }

    private static void parseDescriptionOnly(String userInput) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("-(in|out)\\s+(?:-d\\s+)?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s+\\$([\\d.]+)");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.find()) {
            direction = matcher.group(1);
            description = matcher.group(2);
            amount = Double.parseDouble(matcher.group(3));
            category = "miscellaneous";
        } else {
            throw new IllegalArgumentException(ErrorMessage.WRONG_ADD_FORMAT.toString());
        }
    }

    private static void parseCategoryOnly(String userInput) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("-(in|out)\\s+(?:-c\\s+)?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s+\\$([\\d.]+)");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.find()) {
            direction = matcher.group(1);
            description = "miscellaneous";
            amount = Double.parseDouble(matcher.group(3));
            category = matcher.group(2);
        } else {
            throw new IllegalArgumentException(ErrorMessage.WRONG_ADD_FORMAT.toString());
        }
    }

    private static void parseOnly(String userInput) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("-(in|out)\\s+\\$([\\d.]+)");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.find()) {
            direction = matcher.group(1);
            description = "miscellaneous";
            amount = Double.parseDouble(matcher.group(2));
            category = "miscellaneous";
        } else {
            throw new IllegalArgumentException(ErrorMessage.WRONG_ADD_FORMAT.toString());
        }
    }
}
