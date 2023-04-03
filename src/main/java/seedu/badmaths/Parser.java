/**
 * Takes in user input and splits it into 2 parts
 * First part is the function name
 * Second part is the todo to be executed in that function
 * Returns the two parts using get methods
 *
 * @param userInput
 */

package seedu.badmaths;

import seedu.badmaths.ui.Ui;

public class Parser {

    protected String userInput;

    public Parser(String userInput) { // List. 1
        this.userInput = userInput;
    }

    public String getCommand() {
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
        }
    }

    public String getToDo() {
        try {
            String[] parts = userInput.split(" ", 2); // List 5
            if (parts.length == 1) {
                return "Invalid todo"; // means there is no todo
            } else {
                return parts[1].trim();
            }
        } catch (IllegalArgumentException e) {
            Ui.printIncorrectFormatEntered();
            return null;
        }
    }
}



