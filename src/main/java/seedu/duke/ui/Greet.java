package seedu.duke.ui;
import seedu.duke.util.OuputText;
public class Greet {


    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void greet() {
        OuputText outputText = new OuputText();
        System.out.println("Hello from\n" + LOGO);
        outputText.showAvailableCommands();
    }
}
