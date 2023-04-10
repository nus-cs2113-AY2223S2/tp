package functionalities.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UnMarkCommandTest {

    @Test
    void unmarkCommandNotNullTest() {
        String uid = " ";
        UnMarkCommand test = new UnMarkCommand(uid);
        assertNotNull(test);
    }
}
