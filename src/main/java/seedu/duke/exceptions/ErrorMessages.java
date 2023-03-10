package seedu.duke.exceptions;

public class ErrorMessages {
    /*
    * Shows the list of error messages which would trigger when user enters an incorrect command/input
     */
    public static void emptyDescriptionErrorMessage(){
        System.out.println("Your generate description cannot be empty!");
    }
    public static void invalidInputMessage(){
        System.out.println("Please key in a valid numeric input!");
    }

    public static void unknownCommandMessage(){
        System.out.println("Sorry! Duke doesn't understand :-( ");
    }

    public static void helpCommandMessage(){
        System.out.println("Type /help to find out the possible commands Duke can read in!");
    }
}
