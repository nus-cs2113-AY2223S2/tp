package seedu.duke.util;

public class OuputText {
    public void showAvailableCommands() {

        System.out.println("These are some commands available: ");
        System.out.println("Get a quick exercise: quick x (where x is a positive integer)");
        System.out.println("Generate a specific list of exercises: generate PARAM1 PARAM2 ... x ");
        System.out.println("PARAM stands for a specific requirement you want to include in your exercise.\n");
        System.out.println("The following parameters are available for Generate command:");
        System.out.println("gym (filters for exercises that need gym equipment)");
        System.out.println("static (filters for static exercises)");
        System.out.println("easy/medium/hard (filters for exercises with a specific difficulty)");
        System.out.println("upper/core/legs (filters for exercises with a specific workout type)");
        //System.out.println(ErrorMessages.ERROR_GLOBAL_INVALID_COMMAND.toString());
    }
}
