package seedu.duke.ui;

public class Bye {
    private static final String EXIT_LOGO = "    ____             __" + System.lineSeparator() +
            "   / __ )__  _____  / /" + System.lineSeparator() +
            "  / __  / / / / _ \\/ / " + System.lineSeparator() +
            " / /_/ / /_/ /  __/_/  " + System.lineSeparator() +
            "/_____/\\__, /\\___(_)   " + System.lineSeparator() +
            "      /____/    ";

    public static void bye() {
        System.out.println(EXIT_LOGO);
        System.out.println("Thanks for using Fitness Duke!" + System.lineSeparator());
        System.out.println("Hope to see you again!" + System.lineSeparator());
    }

}
