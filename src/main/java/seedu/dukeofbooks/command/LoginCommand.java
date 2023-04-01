package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.AccessController;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.user.User;
import seedu.dukeofbooks.data.user.UserRecords;

public class LoginCommand extends AccessCommand {
    public static final String COMMAND_WORD = "login";
    public static final String COMMAND_USAGE = "login -username [username] -password [password]";
    private static final String SUCCESS_MESSAGE = "Login Success!";

    private final String username;
    private final String password;

    public LoginCommand(UserRecords userRecords, String username, String password) {
        super(userRecords);
        this.username = username;
        this.password = password;
    }

    @Override
    public AccessResponse execute() {
        try {
            User user = AccessController.loginAccount(userRecords, username, password);
            return new AccessResponse(user, SUCCESS_MESSAGE);
        } catch (IllegalValueException ive) {
            return new AccessResponse(null, ive.getMessage());
        }
    }
}
