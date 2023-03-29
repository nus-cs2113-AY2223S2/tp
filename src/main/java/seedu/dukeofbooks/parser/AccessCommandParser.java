package seedu.dukeofbooks.parser;

import seedu.dukeofbooks.command.AccessCommand;
import seedu.dukeofbooks.command.IncorrectAccessCommand;
import seedu.dukeofbooks.command.LoginCommand;
import seedu.dukeofbooks.command.SignupCommand;
import seedu.dukeofbooks.command.ExitCommand;
import seedu.dukeofbooks.data.user.UserRecords;


public class AccessCommandParser implements IParser {
    private final UserRecords userRecords;

    private static <T> int indexOf(T[] arr, T toFind) {
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i].equals(toFind)) {
                return i;
            }
        }
        return -1;
    }

    public AccessCommandParser(UserRecords userRecords) {
        this.userRecords = userRecords;
    }

    @Override
    public AccessCommand parseCommand(String userInput) {
        String[] args = userInput.split(" ");
        for (int i = 0; i < args.length; ++i) {
            args[i] = args[i].trim();
        }
        switch (args[0]) {
        case (LoginCommand.COMMAND_WORD):
            return parseLoginCommand(args);
        case (SignupCommand.COMMAND_WORD):
            return parseSignupCommand(args);
        case (ExitCommand.COMMAND_WORD):
            return new ExitCommand();
        default:
            return new IncorrectAccessCommand("Unknown Command!");
        }
    }

    private AccessCommand parseLoginCommand(String[] args) {
        assert args[0].equals(LoginCommand.COMMAND_WORD);
        int usernameIndex = indexOf(args, "-username");
        int passwordIndex = indexOf(args, "-password");
        if (usernameIndex == -1 || passwordIndex == -1) {
            return new IncorrectAccessCommand("Cannot find username or password!");
        }

        try {
            String username = args[usernameIndex + 1];
            String password = args[passwordIndex + 1];
            assert !username.isEmpty();
            assert !password.isEmpty();
            return new LoginCommand(userRecords, username, password);
        } catch (IndexOutOfBoundsException | AssertionError e) {
            return new IncorrectAccessCommand("Username or password is empty!");
        }
    }

    private AccessCommand parseSignupCommand(String[] args) {
        assert args[0].equals(SignupCommand.COMMAND_WORD);
        int usernameIndex = indexOf(args, "-username");
        int passwordIndex = indexOf(args, "-password");
        int nameIndex = indexOf(args, "-name");
        if (usernameIndex == -1 || passwordIndex == -1 || nameIndex == -1) {
            return new IncorrectAccessCommand("Cannot find username or password or name!");
        }
        try {
            String username = args[usernameIndex + 1];
            String password = args[passwordIndex + 1];
            String name = args[nameIndex + 1];
            assert !username.isEmpty();
            assert !password.isEmpty();
            assert !name.isEmpty();
            return new SignupCommand(userRecords, username, password, name);
        } catch (IndexOutOfBoundsException | AssertionError e) {
            return new IncorrectAccessCommand("Username or password or name is empty!");
        }


    }
}
