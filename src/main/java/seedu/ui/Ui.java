package seedu.ui;


import java.util.Scanner;

public class Ui {

    private static final String LINE = "=======================================";
    private static final String LINE_SEPARATOR = "----------------------------------------";

    private static final String WELCOME_MESSAGE = "Welcome to FITZ! Time to record your daily workouts and calories consumption!\n"
            + "Enter \"/whelp\" "
            + "if you need the list of commands for workouts record.\n"
            + "Enter \"/chelp\" "
            + "if you need the list of commands for calories record.\n"
            + LINE_SEPARATOR;
    private static final String LOGO_MESSAGE = " _______  __  .___________.________  \n" +
            "|   ____||  | |           |       /  \n" +
            "|  |__   |  | `---|  |----`---/  /   \n" +
            "|   __|  |  |     |  |       /  /    \n" +
            "|  |     |  |     |  |      /  /----.\n" +
            "|__|     |__|     |__|     /________|";
    private static final Scanner in = new Scanner(System.in);
    private static final String HELP_MESSAGE_WORKOUT =
            "Here are the list of commands that you can use for workout record:" +System.lineSeparator() + LINE + System.lineSeparator()
                    + "- [Start a new day: /wday])" + System.lineSeparator()
                    + "- [Start a workout: /wstart])" + System.lineSeparator()
                    + "- [Add exercise: /wadd]" + System.lineSeparator()
                    + "- [Display all the days: /wlist]" + System.lineSeparator()
                    + "- [Display workouts information for a specific day: /wview]" + System.lineSeparator()
                    + "- [Display total amount of reps and set for one week /wcount]" + System.lineSeparator()
                    + "- [Delete workouts: /wdelete]" + System.lineSeparator()
                    + "- [Exit app: /exit]" + System.lineSeparator() + LINE;
    private static final String HELP_MESSAGE_CALORIES =
            "Here are the list of commands that you can use for calories record:" +System.lineSeparator() + LINE + System.lineSeparator()
                    + "- [Start a new day: /cday])" + System.lineSeparator()
                    + "- [Add food and calories: /cadd]" + System.lineSeparator()
                    + "- [Display total calories consumption: /wlist]" + System.lineSeparator()
                    + "- [Display calories consumed on a specific date : /cview]" + System.lineSeparator()
                    + "- [Delete calories record for one food: /wdelete]" + System.lineSeparator()
                    + "- [Exit app: /exit]" + System.lineSeparator() + LINE;

    public static void showGreeting() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void showLogo(){
        System.out.println(LOGO_MESSAGE);
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

    public static String getWorkoutHelpMessage() {
        return HELP_MESSAGE_WORKOUT;
    }

    public static String getCaloriesHelpMessage() {
        return HELP_MESSAGE_CALORIES;
    }
}
