package functionalities;
import exception.SniffException;
import java.util.logging.Logger;

public class Parser {
    protected static Command command;
    static Logger logger = Logger.getLogger("parser");
    private static final int NA = 0;
    private static final String na = "";

    public static Command parse(String userCommand) throws SniffException {
        String task = userCommand.trim();
        if (task.toLowerCase().startsWith("add")) {
            parseAddCommand(task);
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

    private static void parseAddCommand(String task) throws SniffException {
        try{
            String userCommand = "add";
            int uidIndex = task.indexOf("aID/");
            int typeIndex = task.indexOf("t/");
            int animalIndex = task.indexOf("a/");
            int nameIndex = task.indexOf("n/");
            int dateIndex = task.indexOf("d/");
            String uid = task.substring(uidIndex + 4, typeIndex - 1);
            String type = task.substring(typeIndex + 2, animalIndex - 1);
            String animal = task.substring(animalIndex + 2, nameIndex - 1);
            String name = task.substring(nameIndex + 2, dateIndex - 1);
            String date = task.substring(dateIndex + 2, task.length());
            command = new Command(userCommand, NA, NA, uid, type, animal, name, date);
        }catch(StringIndexOutOfBoundsException e){
            throw new SniffException(" The add command description cannot be empty!");
        }
    }

    private static void parseViewCommand(String task) throws SniffException {
        try {
            String userCommand = "view";
            int uId = Integer.parseInt(task.split(" ", 2)[1]);
            command = new Command(userCommand, NA, uId, na, na, na, na, na);
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
        command = new Command(userCommand, NA, NA, na, na, na, na, na);
    }

    private static void parseRemoveCommand(String task) throws SniffException {
        try {
            String userCommand = "remove";
            int appointmentNum = Integer.parseInt(task.split(" ", 2)[1]);
            command = new Command(userCommand, appointmentNum, NA, na, na, na, na, na);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SniffException(" The remove command description cannot be empty!");
        } catch (NumberFormatException e) {
            throw new SniffException(" The remove command description must be a number!");
        }
    }

    private static void parseByeCommand() {
        String userCommand = "bye";
        command = new Command(userCommand, NA, NA, na, na, na, na, na);
    }
}
