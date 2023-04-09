package seedu.duke;
import seedu.duke.save.Storage;
import seedu.duke.ui.Menu;
import seedu.duke.ui.Parser;

import java.util.Scanner;

public class Duke {

    public static int hash = 0; // Stores hash of user's password

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    //@@author JeraldChen
    public static void main(String[] args) {
        String logo = " ____         ____        _        \n"
                + "|  _ \\  ___  |  _ \\ _  _ | | _____ \n"
                + "| | | |/ _ \\ | | | | | | | |/ / _ \\\n"
                + "| |_| | |    | |_| | |_| |   <  __/\n"
                + "|____/|_|    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo);
        Storage.loadData();
        while (hash == 0) {
            Menu.showWelcomeMenu();
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            Parser.parseWelcome(input);
        }
        while (true) {
            Menu.showAccountMenu();
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            input = input.trim();
            Parser.parseAccountCommand(input);
        }
    }

    /**
     * Sets the password.
     * @author Thunderdragon221
     *
     * @param userPassword password to be set.
     */
    public static void setPassword(int userPassword) {
        hash = userPassword;
    }

    /**
     * Returns the password.
     *
     * @return hashedPassword.
     */
    public static int getPassword() {
        return hash;
    }
}
