package seedu.duke;

/*
import seedu.duke.diagnosis.Diagnosis;
import seedu.duke.diagnosis.IllnessMatch;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;
 */

import seedu.duke.save.Storage;
import seedu.duke.ui.Menu;
import seedu.duke.ui.Parser;

import java.util.Scanner;

public class Duke {

    public static String password = ""; // Stores user's password

    /**
     * Main entry-point for the java.duke.Duke application.
     * @author Jeraldchen
     */
    public static void main(String[] args) {
        String logo = " ____         ____        _        \n"
                + "|  _ \\  ___  |  _ \\ _  _ | | _____ \n"
                + "| | | |/ _ \\ | | | | | | | |/ / _ \\\n"
                + "| |_| | |    | |_| | |_| |   <  __/\n"
                + "|____/|_|    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo);
        Storage.loadData();
        while (password.equals("")) {
            Menu.showWelcomeMenu();
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            Parser.parseWelcome(input);
        }
        while (true) {
            Menu.showAccountMenu();
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            Parser.parseAccountCommand(input);
        }
    }

    /**
     * Sets the password.
     * @author Thunderdragon221
     *
     * @param userPassword password to be set.
     */
    public static void setPassword(String userPassword) {
        password = userPassword;
    }

    /**
     * Returns the password.
     *
     * @return password.
     */
    public static String getPassword() {
        return password;
    }

}
