package seedu.badmaths;

import org.junit.jupiter.api.Test;
import seedu.badmaths.commands.IntegerChecker;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class IntegerCheckerTest {
    @Test
    public void testIsAnInt() {
        assertTrue(IntegerChecker.isAnInt("1234567890"));
        assertFalse(IntegerChecker.isAnInt("not a number"));
    }
}
