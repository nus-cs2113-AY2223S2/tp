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
    }

    /**
     * Prints a welcome message for users when application is launched
     */
    public static void showWelcome() {
        String logo = "_______   ____ ___  ___________________.__\n"
                + " \\      \\ |    |   \\/   _____/\\______   \\  | _____    ____   ____   ___________ \n"
                + " /   |   \\|    |   /\\_____  \\  |     ___/  | \\__  \\  /    \\ /    \\_/ __ \\_  __\\ \n"
                + "/    |    \\    |  / /        \\ |    |   |  |__/ __ \\|   |  \\   |  \\  ___/|  | \\/\n"
                + "\\____|__  /______/ /_______  / |____|   |____(____  /___|  /___|  /\\___  >__|   \n"
                + "        \\/                 \\/                     \\/     \\/     \\/     \\/       \n";
        System.out.println("Hello from\n" + logo);
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
     * Prints error message for users when there is unrecognised command
     */
    public static void addErrorMsg() {
        printDash();
        System.out.println("Sorry, I don't understand you!");
        printDash();
    }

    /**
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
}
