package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    /**
     * Prints a line of dashes for better readability
     */
    public static void printDash() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Obtains user input and interprets what needs to be performed by certain keywords.
     */
    public static void getUserCommand(EventList eventList) {
        Scanner in = new Scanner(System.in);

        String cmd;
        cmd = in.nextLine();

        while (!(cmd.equals("bye"))) {
            Parser.parseCommand(cmd, eventList);
            cmd = in.nextLine();
        }

        in.close();
    }

    /**
     * Prints a welcome message for users when application is launched
     */
    public static void showWelcome() {
        String logo = "???";
        System.out.println(logo + "Hello there! What can we do for you today?");
        printDash();
    }

    /**
     * Prints success message for users when event is added
     */
    public static void addSuccessMsg(String taskDetail) {
        printDash();
        System.out.println("Event successfully added: "+System.lineSeparator());
        System.out.println("    > " + taskDetail);
        printDash();
    }

    /**
     * Prints success message for
     * users when event is edited
     */
    public static void addSuccessEditMsg() {
        printDash();
        System.out.println("Event successfully edited!");
        printDash();
    }

    /**
     * Prints error message for
     * users when there is unrecognised
     * command
     * Prints error message for users when there is unrecognised command
     */
    public static void addErrorMsg() {
        printDash();
        System.out.println("Sorry, I don't understand you!");
        printDash();
    }

    /*
     * Prints success message for users when event is deleted
     */
    public static void deleteSuccessMsg(String taskDetail) {
        printDash();
        System.out.println("This event is deleted: "+System.lineSeparator());
        System.out.println("    > "+taskDetail);
        printDash();
    }

    /**
     * Prints list of events
     */
    public static void listTask(ArrayList<Event> eventList) {
        printDash();
        if (eventList.size() == 0) {
            System.out.println("There are no events!");
            printDash();
            return;
        }
        for (int i = 0; i < eventList.size(); i++) {
            System.out.println("   > " + Integer.toString(i + 1) + "." + eventList.get(i).toString());
        }
        printDash();
    }

    /**
     * Prints an exit message when user intends to exit Duke
     */
    public static void printExit() {
        printDash();
        System.out.println("Bye, see ya soon!");
        printDash();
    }

    public static void printErrorMsg (String errorMessage) {
        printDash();
        System.out.println(errorMessage);
        printDash();
    }

    public static void editSuccessMsg(String description, String time) {
        printDash();
        System.out.println("Time of event: " + description + " is changed to: ");
        System.out.println("    > " + time);
        printDash();
    }

    public static void deleteAllSuccess() {
        printDash();
        System.out.println("    > all events are deleted!");
        printDash();
    }
}
