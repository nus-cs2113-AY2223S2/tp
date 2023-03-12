package seedu.duke.ui;

public class Greet {

    private static final String LOGO = "    _______ __                          ____        __      \n" +
            "   / ____(_) /_____  ___  __________   / __ \\__  __/ /_____ \n" +
            "  / /_  / / __/ __ \\/ _ \\/ ___/ ___/  / / / / / / / //_/ _ \\\n" +
            " / __/ / / /_/ / / /  __(__  |__  )  / /_/ / /_/ / ,< /  __/\n" +
            "/_/   /_/\\__/_/ /_/\\___/____/____/  /_____/\\__,_/_/|_|\\___/ ";

    public static void greet() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("These are some commands available: ");
        System.out.println("Get a quick exercise: quick x (where x is a positive integer)");
        System.out.println("Generate a specific list of exercises: generate PARAM1 PARAM2 ... x ");
        System.out.println("PARAM stands for a specific requirement you want to include in your exercise.\n");
        System.out.println("The following parameters are available for Generate command:");
        System.out.println("gym (filters for exercises that need gym equipment)");
        System.out.println("static (filters for static exercises)");
        System.out.println("easy/medium/hard (filters for exercises with a specific difficulty)");

    }
}
