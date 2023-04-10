package functionalities.commands;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class FindCommandTest {

    @Test
    void findCommandNotNull() {
        String category = "animal";
        String details = "cat";
        FindCommand f = new FindCommand(category, details);
        assertNotNull(f);
    }
}
