package seedu.duke.ui;

public class Help {
    public static void helpList(){
        System.out.println("Here is the list of commands which Duke can interpret:");
        System.out.println("bye/exit -> Exits Fitness Duke program");
        System.out.println("generate x -> Generates x number of exercises at random, " +
                "where x is a positive integer greater than 0");

    }
}
