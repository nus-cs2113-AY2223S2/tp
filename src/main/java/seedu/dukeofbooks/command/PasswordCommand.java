package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.AccessController;
import seedu.dukeofbooks.data.user.UserRecords;

public class PasswordCommand extends AccessCommand {
    public static final String COMMAND_WORD = "password";
    public static final String COMMAND_USAGE = "password -username [username] -old [old password] -new [new " +
            "password]";
    private static final String CANNOT_FIND_USER = "Cannot find user!";
    private static final String WRONG_PASSWORD = "The old password is incorrect!";
    private static final String SUCCESS_MSG = "Password has been reset!";

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
    public AccessResponse execute() {
        if (userRecords.getUser(username) == null) {
            return new AccessResponse(null, CANNOT_FIND_USER);
        }
        if (AccessController.changePassword(userRecords, username, prevPassword, newPassword)) {
            return new AccessResponse(null, SUCCESS_MSG);
        } else {
            return new AccessResponse(null, WRONG_PASSWORD);
        }
    }
}
