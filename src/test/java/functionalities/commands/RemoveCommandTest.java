package functionalities.commands;

import exception.SniffException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoveCommandTest {

    @Test
    void RemoveCommandNotNullTest() {
        String uid = "";
        RemoveCommand c = new RemoveCommand(uid);
        assertNotNull(c);
    }
}