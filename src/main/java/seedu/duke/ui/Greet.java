package seedu.duke.ui;

public class Greet {

    private static final String LOGO = "    _______ __                          ____        __      \n" +
            "   / ____(_) /_____  ___  __________   / __ \\__  __/ /_____ \n" +
            "  / /_  / / __/ __ \\/ _ \\/ ___/ ___/  / / / / / / / //_/ _ \\\n" +
            " / __/ / / /_/ / / /  __(__  |__  )  / /_/ / /_/ / ,< /  __/\n" +
            "/_/   /_/\\__/_/ /_/\\___/____/____/  /_____/\\__,_/_/|_|\\___/ ";

    public static void greet() {
        PrintHelpMessage printHelpMessage = new PrintHelpMessage();
        System.out.println("Hello from\n" + LOGO);
        printHelpMessage.showAvailableCommands();
    }

}
