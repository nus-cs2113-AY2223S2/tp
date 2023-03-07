package seedu.moneymind;

public class Command {
    private CommandType type;
    private String value;

    public Command(String input) throws InvalidCommandException {
        String[] tokens = input.split(" ", 2);

        String command = tokens[0];

        // assign command type only
        switch (command) {
        case "bye":
            this.type = CommandType.EXIT;
            break;
        case "echo":
            this.type = CommandType.ECHO;
            break;
        default:
            throw new InvalidCommandException();
        }

        // process value based on command type, only for commands with parameters
        switch (type) {
        case ECHO:
            this.value = tokens[1];
            break;
        default:
            break;
        }
    }

    public void execute(Ui ui) {
        switch (type) {
        case ECHO:
            ui.echo(value);
            break;
        default:
            break;
        }
    }

    public boolean isExit() {
        return type == CommandType.EXIT;
    }
}

enum CommandType {
    ECHO, EXIT
}

