/**
 * Takes in user input and splits it into 2 parts
 * First part is the function name
 * Second part is the todo to be executed in that function
 * Returns the two parts using get methods
 *
 * @param userInput
 */

package seedu.badMaths;

import seedu.badMaths.ui.Ui;

public class Parser {

    protected String userInput;

    public Parser(String userInput) { // Bye. abdscscs
        this.userInput = userInput;
    }

    public String getCommand() {
<<<<<<< .merge_file_a27836
        if (userInput.contains(".")) {
            return userInput.substring(0, userInput.indexOf(".")); // Bye
        } else {
            return "Invalid command";
=======
        try {
            String trimmedInput = userInput.trim();
            if (trimmedInput.contains(" ")) {
                int indexOfSpace = trimmedInput.indexOf(" ");
                String command = trimmedInput.substring(0, indexOfSpace);
                return command;
            } else {
                return userInput;
            }
        } catch (IllegalArgumentException e) {
            Ui.printIncorrectFormatEntered();
            return null;
>>>>>>> .merge_file_a30848
        }
    }

    public String getToDo() {
<<<<<<< .merge_file_a27836
        if (userInput.contains(".") && userInput.contains(" ")) {
            return userInput.substring(userInput.indexOf(".") + 2); // absbsds
        } else {
            return "Invalid todo";
=======
        try {
            String[] parts = userInput.split(" ", 2);
            if (parts.length == 1) {
                return "Invalid todo";
            } else {
                return parts[1].trim();
            }
        } catch (IllegalArgumentException e) {
            Ui.printIncorrectFormatEntered();
            return null;
>>>>>>> .merge_file_a30848
        }
    }
}
