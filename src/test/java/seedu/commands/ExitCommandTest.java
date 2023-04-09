package seedu.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


//@@author ZIZI-czh
public class ExitCommandTest {
    @Test
    public void testIsExit() {
        ExitCommand exitCommand = new ExitCommand();
        assertTrue(ExitCommand.isExit(exitCommand));
    }

    @Test
    public void testExecute() {
        ExitCommand exitCommand = new ExitCommand();
        String expected = "Thank you, hope you had a great workout!!!"
                + System.lineSeparator() + "=======================================";
        assertEquals(expected, exitCommand.execute());
    }

}
