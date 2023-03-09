package seedu.pettracker.parser;

import org.junit.jupiter.api.Test;
import seedu.pettracker.commands.ExitCommand;
import seedu.pettracker.commands.ListPetCommand;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandParserTest {

    @Test
    void parseCommand() {
        CommandParser cp = new CommandParser();
        assertAll(() -> assertTrue(cp.newCommand("list") instanceof ListPetCommand),
                () -> assertTrue(cp.newCommand("exit") instanceof ExitCommand)
        );
    }
}