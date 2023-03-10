package seedu.moneymind;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MoneymindTest {
    @Test
    public void initialize_null_noExceptionThrown() {
        assertDoesNotThrow(() -> {
            Moneymind moneymind = new Moneymind();
        });
    }
}
