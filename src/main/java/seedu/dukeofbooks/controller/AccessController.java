package seedu.dukeofbooks.controller;

import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.user.User;
import seedu.dukeofbooks.data.user.UserRecords;

public class AccessController {
    public static User createAccount(UserRecords userRecords, String username, String password, String name)
            throws IllegalValueException {
        return userRecords.createAccount(username, password, name);
    }

    public static User createAccount(UserRecords userRecords, String username, String password, String name, int phone)
            throws IllegalValueException {
        return userRecords.createAccount(username, password, name, phone);
    }

    public static User loginAccount(UserRecords userRecords, String username, String password) {
        User toCheck = userRecords.getUser(username);
        if (toCheck == null) {
            return null;
        } else if (!toCheck.verifyPassword(password)){
            return null;
        } else {
            return userRecords.getUser(username);
        }
    }

    public static boolean changPassword(UserRecords userRecords, String username, String prevPassword,
                                        String newPassword) {
        User user = userRecords.getUser(username);
        if (user == null) {
            return false;
        }
        if (!user.verifyPassword(prevPassword)) {
            return false;
        } else {
            return user.changePassword(prevPassword, newPassword);
        }
    }

    public static boolean deleteAccount(UserRecords userRecords, String username, String password) {
        if (userRecords.getUser(username) == null) {
            return false;
        }
        return userRecords.deleteAccount(username, password);
    }
}
