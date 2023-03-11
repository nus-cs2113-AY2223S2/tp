package seedu.duke.ui;

public class Greet {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void greet() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Commands: ");
        System.out.println("Get a quick exercise: quick x (where x is a positive integer)");
        System.out.println("Generate a specific list of exercises: generate PARAM1 PARAM2 ... x ");
        System.out.println("PARAM stands for a specific requirement you want your exercise to have");
        System.out.println("Parameters available for Generate:");
        System.out.println("gym (filters for exercises that need gym equipment)");
        System.out.println("static (filters for static exercises)");
        System.out.println("easy/medium/hard (filters for exercises with a specific diffculty)");

    }
}
