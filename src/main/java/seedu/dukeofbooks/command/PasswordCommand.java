package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.AccessController;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.user.User;
import seedu.dukeofbooks.data.user.UserRecords;

public class PasswordCommand extends AccessCommand {
    public static final String COMMAND_WORD = "password";
    public static final String COMMAND_USAGE = "password -username [username] -old [old password] -new [new " +
            "password]";
    private final String username;
    private final String prevPassword;
    private final String newPassword;

    public PasswordCommand(UserRecords userRecords, String username, String prevPassword, String newPassword) {
        super(userRecords);
        this.username = username;
        this.prevPassword = prevPassword;
        this.newPassword = newPassword;
    }

    @Override
    public User execute() throws IllegalValueException {
        if (userRecords.getUser(username) == null) {
            throw new IllegalValueException("Cannot find user!");
        }
        if (AccessController.changPassword(userRecords, username, prevPassword, newPassword)) {
            return userRecords.getUser(username);
        } else {
            throw new IllegalValueException("Old password is not correct!");
        }
    }
}
