package seedu.duke.ui;


public class Greet {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void greet(){
        System.out.println("Hello from\n" + LOGO);
        //System.out.println("What is your name?");
        //Scanner in = new Scanner(System.in);
        //System.out.println("Hello " + in.nextLine());
        System.out.println("type: generate x (where x is a positive integer)");
    }
}
