package seedu.duke.ui;


public class Greet {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void greet(){
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Commands: ");
        System.out.println("Generate a number of random exercises: generate x (where x is a positive integer)");
        System.out.println("Generate a exercises with specific difficulty: generate difficulty easy/medium/hard x ");
    }
}
