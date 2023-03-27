package seedu.duke.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {

    @Test
    void setAutoSave() {
        SessionManager.setAutoSave(true);
        assertTrue(SessionManager.getAutoSave());
        SessionManager.setAutoSave(false);
        assertFalse(SessionManager.getAutoSave());
    }
}