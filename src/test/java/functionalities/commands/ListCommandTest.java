package functionalities.commands;

import exception.SniffException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class ListCommandTest {

    @Test
    void ListCommandNotNullTest() {
        ListCommand c = new ListCommand();
        assertNotNull(c);
    }
}