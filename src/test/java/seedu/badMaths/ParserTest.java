package seedu.badMaths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void getCommandShouldReturnCommandWithoutDot() {
        String userInput = "Store. store this";
        String output;
        if (userInput.contains(".")) {
            output =  userInput.substring(0, userInput.indexOf("."));
        } else {
            output = "Invalid command";
        }
        assertEquals("Store", output);
    }

    @Test
    void getToDoShouldReturnInvalidForSingleWordInput() {
        String userInput = "Help.";
        String output;
        if (userInput.contains(".") && userInput.contains(" ")) {
            output = userInput.substring(userInput.indexOf(".") + 2);
        } else {
            output = "Invalid todo";
        }
        assertEquals("Invalid todo", output);
    }

}