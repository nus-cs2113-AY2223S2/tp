/**
 * Takes in user input and splits it into 2 parts
 * First part is the function name
 * Second part is the command to be executed in that function
 * Returns the two parts using get methods
 *
 * @param userInput
 */

package seedu.badMaths;

public class Parser {

    protected String userInput;

    public Parser (String userInput) {
        this.userInput = userInput;
    }

    public int findDotIndex (String userInput) {
        return userInput.indexOf(".");
    }

    public String getFunction() {
        return userInput.substring(0,findDotIndex(userInput));
    }

    public String getCommand() {
        return userInput.substring(findDotIndex(userInput) + 2);
    }
}
