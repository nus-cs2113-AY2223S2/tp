package seedu;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.commands.ExitCommand;
import seedu.ui.Ui;

public class DukeTest {
    private Duke duke;

    @BeforeEach
    public void setUp() {
        duke = new Duke();
    }

    @Test
    public void testDukeConstructor() {
        try {
            Duke.class.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            fail("Failed to instantiate Duke object");
        }
    }

    @Test
    public void testIsExit() {
        assertTrue(ExitCommand.isExit(new ExitCommand()));
    }

    @Test
    public void testExitCommand() {
        String expected = "Thank you, hope you had a great workout!!!"
                + System.lineSeparator()
                + Ui.line();
        String actual = new ExitCommand().execute();
        assertEquals(expected, actual);
    }
}
