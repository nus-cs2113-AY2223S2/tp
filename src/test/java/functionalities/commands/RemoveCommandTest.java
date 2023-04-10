package functionalities.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RemoveCommandTest {

    @Test
    void removeCommandNotNullTest() {
        String uid = "C12345678A";
        RemoveCommand c = new RemoveCommand(uid);
        assertNotNull(c);
    }
}
