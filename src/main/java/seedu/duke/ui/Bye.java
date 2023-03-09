package seedu.duke.ui;

public class Bye {
    private static final String EXIT_LOGO = "    ____             __\n" +
            "   / __ )__  _____  / /\n" +
            "  / __  / / / / _ \\/ / \n" +
            " / /_/ / /_/ /  __/_/  \n" +
            "/_____/\\__, /\\___(_)   \n" +
            "      /____/    ";
    private static final String MARGIN = "*-----------------------------------------------------------------*";

    public static void bye(){
        System.out.println(MARGIN);
        System.out.println(EXIT_LOGO );
        System.out.println("Hope to see you again!\n");
        System.out.println(MARGIN);
    }
}
