package functionalities.commands;

import exception.SniffException;
import functionalities.SniffTasks;
import functionalities.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListCommandTest {

    @Test
    void ListCommandNotNullTest() throws SniffException {
        ListCommand c = new ListCommand();
        assertNotNull(c);
    }
}