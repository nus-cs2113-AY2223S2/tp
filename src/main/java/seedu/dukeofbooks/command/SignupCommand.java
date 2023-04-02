package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.AccessController;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.user.User;
import seedu.dukeofbooks.data.user.UserRecords;

public class SignupCommand extends AccessCommand {
    public static final String COMMAND_WORD = "signup";
    public static final String COMMAND_USAGE = "signup -username [username] -password [password] -name [name]";
    private static final String SIGNUP_SUCCESS = "Sign up success!";

    private final String username;
    private final String name;
    private final String password;

    public SignupCommand(UserRecords userRecords, String username, String password, String name) {
        super(userRecords);
        this.username = username;
        this.password = password;
        this.name = name;
    }

    @Override
    public AccessResponse execute() {
        try {
            User user = AccessController.createAccount(userRecords, username, password, name);
            return new AccessResponse(user, SIGNUP_SUCCESS);
        } catch (IllegalValueException ive) {
            return new AccessResponse(null, ive.getMessage());
        }
    }
}
