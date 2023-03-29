package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.AccessController;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.user.User;
import seedu.dukeofbooks.data.user.UserRecords;

public class SignupCommand extends AccessCommand {
    public static final String COMMAND_WORD = "signup";
    public static final String USAGE = "signup -username [username] -password [password] -name [name]";

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
    public User execute() throws IllegalValueException {
        return AccessController.createAccount(userRecords, username, password, name);
    }
}
