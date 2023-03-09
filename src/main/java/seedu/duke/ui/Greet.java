package seedu.duke.ui;
import seedu.duke.exceptions.ErrorMessages;

public class Greet {
    private static final String MARGIN = "*-----------------------------------------------------------------*";
    private static final String LOGO = "        _______ __                          ____        __      \n" +
            "       / ____(_) /_____  ___  __________   / __ \\__  __/ /_____ \n" +
            "      / /_  / / __/ __ \\/ _ \\/ ___/ ___/  / / / / / / / //_/ _ \\\n" +
            "     / __/ / / /_/ / / /  __(__  |__  )  / /_/ / /_/ / ,< /  __/\n" +
            "    /_/   /_/\\__/_/ /_/\\___/____/____/  /_____/\\__,_/_/|_|\\___/ ";



    public static void greet(){
        System.out.println(MARGIN);
        System.out.println("Hello! Welcome to\n" + LOGO);
        System.out.println("\n");
        System.out.println("What type of exercise you like to do today?");
        //System.out.println("Enter 'random' to let Duke choose a list of random exercises for you, or 'customised' to choose your preferred exercises.");
        System.out.println(MARGIN);
    }

}
