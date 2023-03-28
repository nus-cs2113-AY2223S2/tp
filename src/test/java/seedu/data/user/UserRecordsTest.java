package seedu.data.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.user.UserRecords;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRecordsTest {
    private UserRecords userRecords;

    @BeforeEach
    void setUp() {
        userRecords = new UserRecords();
    }

    @Test
    public void testRootUser() {
        assertNotNull(userRecords.getUser("root"));
        assertTrue(userRecords.getUser("root").verifyPassword("root"));
    }

    @Test
    void testCreateAccountSuccess() {
        assertDoesNotThrow(() -> {
            assertNotNull(userRecords.createAccount(
                    "alice", "password", "alice"));
            assertNotNull(userRecords.getUser("alice"));
        });
        assertDoesNotThrow(() -> {
            assertNotNull(userRecords.createAccount(
                    "bob", "password", "bob",
                    12345678));
            assertNotNull(userRecords.getUser("bob"));
        });
    }

    @Test
    void testCreateAccountFail() {
        assertDoesNotThrow(() -> {
            assertNotNull(userRecords.createAccount(
                    "alice", "password", "alice"));
            assertNotNull(userRecords.getUser("alice"));
        });
        try {
            userRecords.createAccount("alice", "123", "alice");
        } catch (IllegalValueException ive) {
            assertEquals( "Duplicate username!", ive.getMessage());
        }
    }

    @Test
    void testDeleteAccount() {
        assertDoesNotThrow(()-> {
            userRecords.createAccount("alice", "password", "alice");
        });
        assertFalse(userRecords.deleteAccount("root", "root"));
        assertNotNull(userRecords.getUser("root"));
        assertFalse(userRecords.deleteAccount("alice", "123"));
        assertTrue(userRecords.deleteAccount("alice", "password"));
        assertNull(userRecords.getUser("alice"));
    }
}
