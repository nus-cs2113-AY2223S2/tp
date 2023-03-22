package functionalities.commands;

import exception.SniffException;
import functionalities.SniffTasks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExitCommandTest {

    @Test
    void ExitCommandNotNullTest() {
        Command c = new ExitCommand();
        assertNotNull(c);
    }
    @Test
    void RemoveCommandTest() throws SniffException {
        SniffTasks tasks = new SniffTasks();
        Command c = new ExitCommand();
        c.executeCommand(tasks);
        assertTrue(c.isExit);
    }
}