package seedu.duke;


public class Ui {
    public static final String LINE = "    ____________________________________________________________";

    /**
     * For greeting and display each time the program run
     */
    public static void greeting() {

        System.out.println(LINE);
        System.out.println("     Hello! type in the command to approach next step");
        System.out.println("     Type bye to exit");
        System.out.println(LINE);
    }
}