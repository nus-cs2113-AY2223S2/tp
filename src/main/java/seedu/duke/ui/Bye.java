package seedu.duke.ui;

public class Bye {
    private static final String EXIT_LOGO = "    ____             __\n" +
            "   / __ )__  _____  / /\n" +
            "  / __  / / / / _ \\/ / \n" +
            " / /_/ / /_/ /  __/_/  \n" +
            "/_____/\\__, /\\___(_)   \n" +
            "      /____/    ";

    public static void bye(){
        System.out.println(EXIT_LOGO);
        System.out.println("Thanks for using Fitness Duke!\n");
        System.out.println("Hope to see you again!\n");
    }

}
