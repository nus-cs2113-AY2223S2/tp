package seedu.data.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.user.User;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class UserTest {
    private final String username = "someone";
    private final String password = "some password";
    private final String name = "someone name";
    private final int phone = 12345678;

    @Test
    public void testCreateUserSuccess() {
        assertDoesNotThrow(() -> {
            User user = new User(username, password, name);
            assertTrue(user.verifyPassword(password));
            assertFalse(user.isSuperUser());
        });
        assertDoesNotThrow(() -> {
            User user = new User(username, password, name, phone);
            assertTrue(user.verifyPassword(password));
            assertFalse(user.isSuperUser());
        });
    }

    @Test
    public void testVerifyPassword() {
        try {
            User user = new User(username, password, name);
            assertTrue(user.verifyPassword(password));
            assertFalse(user.verifyPassword("123456"));
        } catch (IllegalValueException ive) {
            fail();
        }
    }

    @Test
    public void testChangePasswordSuccess() {
        try {
            User user = new User(username, password, name);
            assertDoesNotThrow(()->{
                String newPassword = "123456";
                assertTrue(user.verifyPassword(password));
                assertTrue(user.changePassword(password, newPassword));
                assertFalse(user.verifyPassword(password));
                assertTrue(user.verifyPassword(newPassword));
            });
        } catch (IllegalValueException ive) {
            fail();
        }
    }

    @Test
    public void testChangePasswordFail() {
        try {
            User user = new User(username, password, name);
            assertFalse(user.changePassword("123456", "123456"));
        } catch (IllegalValueException ive) {
            fail();
        }
    }

    @Test
    public void testSetSuperUser() {
        try {
            User user = new User(username, password, name);
            assertFalse(user.isSuperUser());
            user.setSuperUser(true);
            assertTrue(user.isSuperUser());
        } catch (IllegalValueException ive) {
            fail();
        }
    }
}
