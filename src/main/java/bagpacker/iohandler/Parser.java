package bagpacker.iohandler;

import bagpacker.commands.AddCommand;
import bagpacker.commands.ByeCommand;
import bagpacker.commands.Command;
import bagpacker.commands.DeleteCommand;
import bagpacker.commands.DeleteListCommand;
import bagpacker.commands.HelpCommand;
import bagpacker.commands.IncorrectCommand;
import bagpacker.commands.ListCommand;
import bagpacker.commands.PackCommand;
import bagpacker.exception.EmptyInputException;
import bagpacker.exception.InvalidIndexException;
import bagpacker.exception.InvalidVariablesException;
import bagpacker.packingfunc.PackingList;

import java.util.Scanner;

/**
 * Parser class contains methods to manipulate user input
 */
public class Parser {
    private static String[] arguments;
    private static String fullInput;

    public static void setFullInput(String fullInput) {
        Parser.fullInput = fullInput;
    }


    /**
     * Returns the user input in String format
     */
    public static String getFullInput() {
        return fullInput;
    }

    /**
     * Returns the user input as in array format
     */
    public static ArrayList<String> getInputStringArray() {
        return inputStringArray;
    }

    public static void setInputStringArray(String[] inputStringArray) {
        Parser.inputStringArray = new ArrayList<>(Arrays.asList(inputStringArray));
    }

    public static Command parse() {
        arguments = new String[2];
        String inputLine = "";
        while (inputLine.isEmpty()) {
            try {
                inputLine = readLine().trim();
            } catch (EmptyInputException e) {
                Ui.errorMessage("Empty input received",
                        "try to input a command, to view all commands input 'help'");
            }
        }
        setFullInput(inputLine);
        String[] inputStringList = inputLine.split(" ");
        setInputStringArray(inputStringList);
        switch (getCommand()) {
        case "add":
            arguments = inputStringList[1].trim().split("/of");
            return createAddObj();
        case "delete":
            arguments[0] = inputStringList[1].trim();
            return createDeleteObj();
        case "pack":
            arguments = inputStringList[1].trim().split("/of");
            return createPackObj();
        case "unpack":
            arguments = inputStringList[1].trim().split("/of");
            return createUnpackObj();
        case "list":
            return createListObj();
        case "help":
            return createHelpObj();
        case "deletelist":
            return createDeletelistObj();
        case "bye":
            return createByeObj();
        default:
            return new IncorrectCommand("'" + inputStringList[0] + "' is an invalid User Command",
                    "input 'help' to receive all valid commands");
        }
    }

    /**
     * Reads and returns the full user input from the command line interface
     * - trims the leading and trailing white spaces
     *
     * @return inputLine the String input of the user
     * @throws EmptyInputException when user input empty line
     */
    public static String readLine() throws EmptyInputException {
        String inputLine;
        Scanner in = new Scanner(System.in);
        inputLine = in.nextLine().trim();
        if (inputLine.isEmpty()) {
            throw new EmptyInputException();
        }
        return inputLine;
    }

    /**
     * Returns a string which represents the index of the item from the user input
     *
     * @return inputIndex which is the item index of the item in packing list
     * @throws InvalidIndexException when the item index is not valid
     */
    public static String getItemIndex() throws InvalidIndexException {
        String inputIndex;
        int itemIndex;
        if (arguments[0] == null) {
            throw new InvalidIndexException();
        }
        try {
            int itemIndStart = fullInput.indexOf(" ") + 1;
            inputIndex = fullInput.substring(itemIndStart);
            itemIndex = Integer.parseInt(inputIndex);
            if (itemIndex < 1 | itemIndex > PackingList.getItemList().size()) {
                throw new InvalidIndexException();
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidIndexException();
        }
        return inputIndex;
    }

    /**
     * Returns a string which represents the relevant variable depending on the command
     * - "add" will return the item name
     * - "delete", "pack", "unpack" will return item index
     *
     * @return itemVariable which is the index OR name of the item in packing list
     * @throws InvalidIndexException when the item index is not valid
     */
    public static String getVariable() throws InvalidIndexException {
        String itemVariable;
        try {
            itemVariable = getItemIndex();
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException();
        }
        return itemVariable;
    }

    public static String[] getQuantityAndDescription() {
        String[] itemQuantityAndDescription = new String[2];
        itemQuantityAndDescription[0] = arguments[0];
        itemQuantityAndDescription[1] = arguments[1];
        return itemQuantityAndDescription;
    }

    public static Command createAddObj() {
        String[] quantityAndDescription = getQuantityAndDescription();
        int itemQuantity = Integer.parseInt((quantityAndDescription[0]).trim());
        String itemDescription = quantityAndDescription[1].trim();
        return new AddCommand(itemQuantity, itemDescription);
    }

    /**
     * Attempts to create DeleteCommand object to be executed where it is called from
     *
     * @return DeleteCommand the command to be executed to delete an item to the packing list, else
     *      an IncorrectCommand is created to be executed
     */
    public static Command createDeleteObj() {
        try {
            String itemIndex = getVariable();
            return new DeleteCommand(Integer.parseInt(itemIndex));
        } catch (InvalidIndexException e) {
            if (PackingList.getItemList().size() == 0) {
                return new IncorrectCommand("Invalid Item Index",
                        "Your packing list is empty, there is nothing to delete");
            } else {
                return new IncorrectCommand("Invalid Item Index",
                        "Try to input an integer number between 1 and " + PackingList.getItemList().size());
            }
        }
    }

    public static int[] getQuantityAndIndex() throws InvalidVariablesException, InvalidIndexException {
        int[] itemQuantityAndIndex = new int[2];
        try {
            itemQuantityAndIndex[0] = Integer.parseInt(arguments[0].trim());
            itemQuantityAndIndex[1] = Integer.parseInt(arguments[1].trim());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidVariablesException();
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
        return itemQuantityAndIndex;
    }

    /**
     * Attempts to create PackCommand object to be executed where it is called from
     *
     * @return PackCommand the command to be executed to Pack an item in the packing list, else
     *      an IncorrectCommand is created to be executed
     */
    public static Command createPackObj() {
        try {
            int[] quantityAndIndex = getQuantityAndIndex();
            int itemQuantity = quantityAndIndex[0];
            int itemIndex = quantityAndIndex[1];
            return new PackCommand(itemQuantity, itemIndex);
        } catch (InvalidVariablesException e) {
            return new IncorrectCommand("Invalid Item Name",
                    "try to input a name (word(s)) to be added into the list");
        } catch (InvalidIndexException e) {
            if (PackingList.getItemList().size() == 0) {
                return new IncorrectCommand("Invalid Item Index",
                        "Your packing list is empty, there is nothing to pack");
            } else {
                return new IncorrectCommand("Invalid Item Index",
                        "Try to input an integer number between 1 and " + PackingList.getItemList().size());
            }
        }
    }


    /**
     * Attempts to create UnpackCommand object to be executed where it is called from
     *
     * @return UnpackCommand the command to be executed to unpack an item in the packing list, else
     *      an IncorrectCommand is created to be executed
     */
    public static Command createUnpackObj() {
        try {
            int[] quantityAndIndex = getQuantityAndIndex();
            int itemQuantity = -1 * quantityAndIndex[0];
            int itemIndex = quantityAndIndex[1];
            return new PackCommand(itemQuantity, itemIndex);
        } catch (InvalidVariablesException e) {
            return new IncorrectCommand("Invalid Item Name",
                    "try to input a name (word(s)) to be added into the list");
        } catch (InvalidIndexException e) {
            if (PackingList.getItemList().size() == 0) {
                return new IncorrectCommand("Invalid Item Index",
                        "Your packing list is empty, there is nothing to unpack");
            } else {
                return new IncorrectCommand("Invalid Item Index",
                        "Try to input an integer number between 1 and " + PackingList.getItemList().size());
            }
        }
    }

    public static Command createListObj() {
        return new ListCommand();
    }

    public static Command createDeletelistObj() {
        return new DeleteListCommand();
    }

    private static Command createHelpObj() {
        return new HelpCommand();
    }

    private static Command createByeObj() {
        return new ByeCommand();
    }
}
