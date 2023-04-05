package seedu.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandlingStringInput {

    static int maxChars = 15;
    static int maxWords = 4;


    private static final String LONG_INPUT = "Input is too long.";


    public static boolean isInputTooLong(String input) {
        if (input.length() > maxChars) {
            System.out.println(LONG_INPUT);
            return false;
        }

        String[] words = input.split("\\s+");

        if (words.length > maxWords) {
            System.out.println(LONG_INPUT);
            return false;
        }
        return true;
    }

    public static boolean isValidCharacter(String input) {
        if (!input.matches("^[a-zA-Z0-9 ]*$")) {
            System.out.println("Input contains invalid characters. Please enter a valid name.");
            return false;
        }
        return true;
    }

    public static boolean isInputValid(String input){
        return !isInputTooLong(input) || !isValidCharacter(input);
    }

    public static boolean isInputMatching(String input){
        Pattern pattern = Pattern.compile("(\\d+)([a-zA-Z]+)");

        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            // Check if number is an integer
            try {
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number for weight");
                return false;
            }

        } else {
            System.out.println("Please enter weight and units.");
            return false;
        }
    }
}
