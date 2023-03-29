package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.AccessController;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.user.User;
import seedu.dukeofbooks.data.user.UserRecords;

public class LoginCommand extends AccessCommand {
    public static final String COMMAND_WORD = "login";
    public static final String COMMAND_USAGE = "login -username [username] -password [password]";

    private final String username;
    private final String password;

    public LoginCommand(UserRecords userRecords, String username, String password) {
        super(userRecords);
        this.username = username;
        this.password = password;
    }

    @Override
    public User execute() throws IllegalValueException {
        return AccessController.loginAccount(userRecords, username, password);
    }
}
