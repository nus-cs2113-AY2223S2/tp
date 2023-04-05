package seedu.ui;


import java.util.Scanner;

public class Ui {

    private static final String WELCOME_MESSAGE = "Let's get moving!\n" + "\"/day <DD/MM/YY>\" to start " +
            "your workouts recording";
    private static final String LOGO_MESSAGE = " _______  __  .___________.________  \n" +
            "|   ____||  | |           |       /  \n" +
            "|  |__   |  | `---|  |----`---/  /   \n" +
            "|   __|  |  |     |  |       /  /    \n" +
            "|  |     |  |     |  |      /  /----.\n" +
            "|__|     |__|     |__|     /________|";
    private static final String LINE = "=======================================";
    private static final String LINE_SEPARATOR = "----------------------------------";
    private static final Scanner in = new Scanner(System.in);
    private static final String HELP_MESSAGE =
            "Here are the list of commands that you can use:" +System.lineSeparator() + LINE + System.lineSeparator()
                    + "- [Start a Workout: /start])" + System.lineSeparator()
                    + "- [Add exercise: /wadd]" + System.lineSeparator()
                    + "- [Add calories: /cadd]" + System.lineSeparator()
                    + "- [End current workout: /end]" + System.lineSeparator()
                    + "- [Display workout list: /list]" + System.lineSeparator()
                    + "- [Display a workout on a specific date : /wview]" + System.lineSeparator()
                    + "- [Display calories consumed on a specific date : /cview]" + System.lineSeparator()
                    + "- [Display the amount of reps and set on a specific exercise /count]" + System.lineSeparator()
                    + "- [Delete a workout: /delete]" + System.lineSeparator()
                    + "- [Exit app: /exit]" + System.lineSeparator() + LINE;

    public static void showGreeting() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void showLogo(){
        System.out.println(LOGO_MESSAGE);
    }

    public static String showLine(){
        return LINE;
    }
    public static void showOneLine(){
        System.out.println(LINE);
    }


    public static String showSeparator(){
        return (LINE_SEPARATOR);
    }

    public static String getUserInput() {
        return in.nextLine();
    }

    public static void showWelcomeMessage() {
        showOneLine();
        showLogo();
        showOneLine();
        showGreeting();
    }

    public static String getHelpMessage() {
        return HELP_MESSAGE;
    }


}
