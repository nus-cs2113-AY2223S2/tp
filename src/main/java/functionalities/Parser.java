package functionalities;
import exception.SniffException;

public class Parser {
    protected static Command command;

    public static Command parse(String userCommand) throws SniffException {
        String task = userCommand.trim();
        if (task.equalsIgnoreCase("add")) {
            //parseAddCommand();
        } else if (task.toLowerCase().startsWith("remove")) {
            //parseRemoveCommand();
        } else if (task.toLowerCase().startsWith("list")) {
            parseListCommand();
        } else if (task.toLowerCase().startsWith("view")) {
            //parseViewCommand();
        } else if (task.equalsIgnoreCase("bye")) {
            parseByeCommand();
        } else {
            throw new SniffException(" Not a recognized Sniff command!");
        }
        return command;
    }
    private static void parseByeCommand() {
        String userCommand = "bye";
        command = new Command(userCommand);
    }

    private static void parseListCommand() {
        String userCommand = "list";
        command = new Command(userCommand);
    }
}
