package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class DukeTest {
    @Test
    public void sampleTest() {
        Duke duke = new Duke();
        assertNotNull(duke);
    }
}
