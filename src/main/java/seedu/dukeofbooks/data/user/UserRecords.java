package seedu.dukeofbooks.data.user;

import seedu.dukeofbooks.data.exception.IllegalValueException;

import java.util.HashMap;
import java.util.Map;

public class UserRecords {
    private static final String ROOT_USERNAME = "root";
    private static final String ROOT_PASSWORD = "root";
    private final Map<String, User> internalRecord;

    public UserRecords() {
        internalRecord = new HashMap<>();
        try {
            // a superuser root is initially created
            User root = new User(ROOT_USERNAME, ROOT_PASSWORD, ROOT_USERNAME);
            root.setSuperUser(true);
            internalRecord.put(ROOT_USERNAME, root);
        } catch (IllegalValueException ive) {
            ive.printStackTrace();
        }
    }

    /**
     * Gets the user using username
     * @param username username to find
     * @return User object with the username, null if user not found.
     */
    public User getUser(String username) {
        return internalRecord.get(username);
    }

    public User createAccount(String username, String password, String name)
            throws IllegalValueException {
        if (internalRecord.containsKey(username)) {
            throw new IllegalValueException("Duplicate username!");
        }

        User newUser = new User(username, password, name);
        internalRecord.put(username, newUser);
        return newUser;
    }

    public User createAccount(String username, String password, String name,
                              int phone) throws IllegalValueException {
        if (internalRecord.containsKey(username)) {
            throw new IllegalValueException("Duplicate username!");
        }

        User newUser = new User(username, password, name, phone);
        internalRecord.put(username, newUser);
        return newUser;
    }

    public boolean deleteAccount(String username, String password) {
        assert internalRecord.containsKey(username);
        if (username.equals("root")) {
            return false;  // root user cannot be deleted
        }
        if (!internalRecord.get(username).verifyPassword(password)) {
            return false;
        } else {
            internalRecord.remove(username);
            return true;
        }
    }
}
