package seedu.duke.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class HelpCommandTest {


    @Test
    void helpCommand_multipleWithRegex_expectSeparateByRegex() {
        String input = "--remove--find--add";
        HelpCommand hc = new HelpCommand(input);
        String[] expectedOutput = {"","remove", "find", "add"};
        assertArrayEquals(expectedOutput,hc.getFilters());
    }

    @Test
    void helpCommand_withSpacesAndRegex_expectRemoveSpaceSeparateRegex() {
        String input = " --   remove      --find--          add";
        HelpCommand hc = new HelpCommand(input);
        String[] expectedOutput = {"","remove", "find", "add"};
        assertArrayEquals(expectedOutput,hc.getFilters());
    }
}
