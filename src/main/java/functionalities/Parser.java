package functionalities;
import exception.SniffException;
import java.util.logging.Logger;

public class Parser {
    protected static Command command;

    private static final int NA = 0;

    static Logger logger = Logger.getLogger("parser");

    public static Command parse(String userCommand) throws SniffException {
        String task = userCommand.trim();
        if (task.toLowerCase().startsWith("add")) {
            parseAddCommand();
        } else if (task.toLowerCase().startsWith("view")) {
            parseViewCommand(task);
        } else if (task.toLowerCase().startsWith("list")) {
            parseListCommand();
        } else if (task.toLowerCase().startsWith("remove")) {
            parseRemoveCommand(task);
        } else if (task.equalsIgnoreCase("bye")) {
            parseByeCommand();
        } else {
            throw new SniffException(" Not a recognized Sniff command!");
        }
        return command;
    }

    private static void parseAddCommand() {
    }

    private static void parseViewCommand(String task) throws SniffException {
        try {
            String userCommand = "view";
            int uId = Integer.parseInt(task.split(" ", 2)[1]);
            command = new Command(userCommand, NA, uId);
        } catch (ArrayIndexOutOfBoundsException emptyView) {
            logger.warning("No appointment ID provided for view command. Unable to execute view command.");
            throw new SniffException(" The view command description cannot be empty!");
        }  catch (NumberFormatException e) {
            logger.warning("Invalid appointment ID format provided. Integer numbers are expected.");
            throw new SniffException(" The user Id to view appointment details must be a number!");
        }
    }

    private static void parseListCommand() {
        String userCommand = "list";
        command = new Command(userCommand, NA, NA);
    }

    private static void parseRemoveCommand(String task) throws SniffException {
        try {
            String userCommand = "remove";
            int appointmentNum = Integer.parseInt(task.split(" ", 2)[1]);
            command = new Command(userCommand, appointmentNum, NA);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SniffException(" The remove command description cannot be empty!");
        } catch (NumberFormatException e) {
            throw new SniffException(" The remove command description must be a number!");
        }
    }

    private static void parseByeCommand() {
        String userCommand = "bye";
        command = new Command(userCommand, NA, NA);
    }
}
