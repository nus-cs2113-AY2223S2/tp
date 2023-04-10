package functionalities.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MarkCommandTest {

    @Test
    void markCommandNotNullTest() {
        String uid = " ";
        MarkCommand test = new MarkCommand(uid);
        assertNotNull(test);
    }
}
