package seedu.duke.ui;

public class Greet {

    private static final String LOGO = "    _______ __                          ____        __      " +
            System.lineSeparator() +
            "   / ____(_) /_____  ___  __________   / __ \\__  __/ /_____ " + System.lineSeparator() +
            "  / /_  / / __/ __ \\/ _ \\/ ___/ ___/  / / / / / / / //_/ _ \\" + System.lineSeparator() +
            " / __/ / / /_/ / / /  __(__  |__  )  / /_/ / /_/ / ,< /  __/" + System.lineSeparator() +
            "/_/   /_/\\__/_/ /_/\\___/____/____/  /_____/\\__,_/_/|_|\\___/ ";

    public static void greet() {
        System.out.println("Hello from" + System.lineSeparator() + LOGO);
        System.out.println("Start your fitness journey! Type [help] to see the things you can do!");
    }

}
