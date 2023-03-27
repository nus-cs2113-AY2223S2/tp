package seedu.duke.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SessionManagerTest {

    @Test
    void setAutoSave() {
        SessionManager.setAutoSave(true);
        Assertions.assertTrue(SessionManager.getAutoSave());
        SessionManager.setAutoSave(false);
        Assertions.assertFalse(SessionManager.getAutoSave());
    }
}
