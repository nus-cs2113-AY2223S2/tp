//@@author Jeraldchen
package seedu.duke.ui;

public class Parser {

    /**
     * Parses the user input for the main menu.
     * @param choice
     */
    public static void parseWelcome (String choice) {
        switch(choice) {
        case "1":
            Menu.register();
            break;
        case "2":
            Menu.login();
            break;
        case "3":
            Menu.exit();
            break;
        default:
            System.out.println("Invalid input!");
            break;
        }
    }
}
