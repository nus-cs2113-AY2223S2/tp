package seedu.Ui;

public class GeneralUi {

    private static String welcomeMessage = "Hello! I am LifeTracker, a program to aid you in keeping fit!";

    private static String line = "----------------------------------------------------------------";
    private static String endingMessage = "Bye! Hope to see you again soon!";

    public static void showLine() {
        System.out.println(line);
    }

    public static void showWelcomeMessage() {
        System.out.println(welcomeMessage);
    }

    public static void showEndingMessage() {
        System.out.println(endingMessage);
    }

}
