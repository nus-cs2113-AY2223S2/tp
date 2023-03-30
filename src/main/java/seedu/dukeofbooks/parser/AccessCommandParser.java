package seedu.dukeofbooks.parser;

import seedu.dukeofbooks.command.AccessCommand;
import seedu.dukeofbooks.command.AccessHelpCommand;
import seedu.dukeofbooks.command.ExitCommand;
import seedu.dukeofbooks.command.IncorrectAccessCommand;
import seedu.dukeofbooks.command.LoginCommand;
import seedu.dukeofbooks.command.PasswordCommand;
import seedu.dukeofbooks.command.SignupCommand;
import seedu.dukeofbooks.data.user.UserRecords;


public class AccessCommandParser implements IParser {
    private final UserRecords userRecords;

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
        case(PasswordCommand.COMMAND_WORD):
            return parsePasswordCommand(args);
        case (ExitCommand.COMMAND_WORD):
            return new ExitCommand();
        case (AccessHelpCommand.COMMAND_WORD):
            // fallthrough
        default:
            return new AccessHelpCommand();
        }
    }

    private <T> int indexOf(T[] arr, T toFind) {
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i].equals(toFind)) {
                return i;
            }
        }
        return -1;
    }

    private AccessCommand parseLoginCommand(String[] args) {
        assert args[0].equals(LoginCommand.COMMAND_WORD);
        int usernameIndex = indexOf(args, "-username");
        int passwordIndex = indexOf(args, "-password");
        if (usernameIndex == -1 || passwordIndex == -1) {
            return new IncorrectAccessCommand("Cannot find username or password!");
        }

        try {
            StringBuilder usernameBuilder = new StringBuilder();
            StringBuilder passwordBuilder = new StringBuilder();
            for (int i = 1; i < args.length; ++i) {
                if (i > usernameIndex && i < passwordIndex) {
                    usernameBuilder.append(args[i]).append(" ");
                } else if (i > passwordIndex) {
                    passwordBuilder.append(args[i]).append(" ");
                }
            }
            String username = usernameBuilder.toString().trim();
            String password = passwordBuilder.toString().trim();
            assert username.length() > 0;
            assert password.length() > 0;
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
            StringBuilder usernameBuilder = new StringBuilder();
            StringBuilder passwordBuilder = new StringBuilder();
            StringBuilder nameBuilder = new StringBuilder();
            for (int i = 1; i < args.length; ++i) {
                if (i > usernameIndex && i < passwordIndex) {
                    usernameBuilder.append(args[i]).append(" ");
                } else if (i > passwordIndex && i < nameIndex) {
                    passwordBuilder.append(args[i]).append(" ");
                } else if (i > nameIndex) {
                    nameBuilder.append(args[i]).append(" ");
                }
            }
            String username = usernameBuilder.toString().trim();
            String password = passwordBuilder.toString().trim();
            String name = nameBuilder.toString().trim();
            assert !username.isEmpty();
            assert !password.isEmpty();
            assert !name.isEmpty();
            return new SignupCommand(userRecords, username, password, name);
        } catch (IndexOutOfBoundsException | AssertionError e) {
            return new IncorrectAccessCommand("Username or password or name is empty!");
        }
    }

    private AccessCommand parsePasswordCommand(String[] args) {
        assert args[0].equals(PasswordCommand.COMMAND_WORD);
        int usernameIndex = indexOf(args, "-username");
        int oldPassIndex = indexOf(args, "-old");
        int newPassIndex = indexOf(args, "-new");
        if (usernameIndex == -1 || oldPassIndex == -1 || newPassIndex == -1) {
            return new IncorrectAccessCommand("Username, old password, new password cannot be found!");
        }
        try {
            StringBuilder usernameBuilder = new StringBuilder();
            StringBuilder oldPassBuilder = new StringBuilder();
            StringBuilder newPassBuilder = new StringBuilder();
            for (int i = 1; i < args.length; ++i) {
                if (i > usernameIndex && i < oldPassIndex) {
                    usernameBuilder.append(args[i]).append(" ");
                } else if (i > oldPassIndex && i < newPassIndex) {
                    oldPassBuilder.append(args[i]).append(" ");
                } else if (i > newPassIndex) {
                    newPassBuilder.append(args[i]).append(" ");
                }
            }
            String username = usernameBuilder.toString().trim();
            String oldPassword = oldPassBuilder.toString().trim();
            String newPassword = newPassBuilder.toString().trim();
            assert !username.isEmpty();
            assert !oldPassword.isEmpty();
            assert !newPassword.isEmpty();
            return new PasswordCommand(userRecords, username,oldPassword, newPassword);
        } catch (IndexOutOfBoundsException | AssertionError e) {
            return new IncorrectAccessCommand("Username or password is(are) empty!");
        }


    }
}
