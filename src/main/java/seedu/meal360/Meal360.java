package seedu.meal360;

import java.util.Scanner;

public class Meal360 {
    /**
     * Main entry-point for the java.duke.Meal360 application.
     */
    public static void main(String[] args) {
        String logo =   " __  __          _ ____  __  __\n" +
                        "|  \\/  |___ __ _| |__ / / / /  \\\n" +
                        "| |\\/| / -_) _` | ||_ \\/ _ \\ () |\n" +
                        "|_|  |_\\___\\__,_|_|___/\\___/\\__/\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
