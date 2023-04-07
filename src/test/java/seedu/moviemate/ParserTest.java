package seedu.moviemate;

import org.junit.jupiter.api.Test;
import seedu.moviemate.command.*;
import seedu.moviemate.parser.Parser;
import seedu.moviemate.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    Ui ui;
    @Test
    public void testParseCommand() {
        Ui ui = new Ui();
        Command command;

        // Test adding a movie to the watch list
        command = Parser.parseCommand("towatch The Matrix", ui);
        assertTrue(command instanceof AddToWatchListCommand);

        // Test removing a movie from the list
        command = Parser.parseCommand("remove watched", ui);
        assertTrue(command instanceof RemoveListCommand);

        // Test viewing a movie detail
        command = Parser.parseCommand("seedetail movie", ui);
        assertTrue(command instanceof SeeDetailCommand);

        // Test an unknown command
        command = Parser.parseCommand("foobar", ui);
        assertTrue(command instanceof HelpCommand);
    }
    @Test
    void testParseIndex() {
        String s = "0";
        assertEquals(0, Parser.parseIndex(s, 0,5));
    }
}
