package seedu.Commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AddMealCommandTest {
    @Test
    void parseInput_EmptyInput_ExpectException() {

        String commandDescriptor = "";
        assertThrows(NumberFormatException.class,
                () -> Integer.parseInt(commandDescriptor));
    }
}
