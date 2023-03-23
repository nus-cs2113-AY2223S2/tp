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
import bagpacker.commands.UnpackCommand;
import bagpacker.exception.EmptyInputException;
import bagpacker.exception.InvalidIndexException;
import bagpacker.exception.InvalidVariablesException;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Parser class contains methods to manipulate user input
 */
public class Parser {
    private static ArrayList<String> inputStringArray;
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
            return createAddObj();
        case "delete":
            return createDeleteObj();
        case "pack":
            return createPackObj();
        case "unpack":
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
            return new IncorrectCommand("'" + Parser.getCommand() + "' is an invalid User Command",
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
     * Returns the user command in lower case
     *
     * @return command from user
     */
    public static String getCommand() {
        String command = getInputStringArray().get(0).toLowerCase();
        return command;
    }

    /**
     * Returns a string which represents the name of the item from the user input
     *
     * @return inputVariables which is the name of the item
     * @throws InvalidVariablesException when the item name cannot be found
     */
    public static String getItemName() throws InvalidVariablesException {
        String itemName;
        if (inputStringArray.size() <= 1) {
            throw new InvalidVariablesException();
        }
        try {
            int itemIndStart = fullInput.indexOf(" ") + 1;
            itemName = fullInput.substring(itemIndStart);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidVariablesException();
        }
        return itemName;
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
        if (inputStringArray.size() != 2) {
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
     * @param command used to determine the type of variable to return
     * @return itemVariable which is the index OR name of the item in packing list
     * @throws InvalidIndexException when the item index is not valid
     */
    public static String getVariable(String command) throws InvalidVariablesException, InvalidIndexException {
        String itemVariable;
        try {
            if (command.equals("add")) {
                itemVariable = getItemName();
            } else {
                itemVariable = getItemIndex();
            }
        } catch (InvalidVariablesException e) {
            throw new InvalidVariablesException();
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException();
        }
        return itemVariable;
    }


    /**
     * Returns the user item description
     */
    public static String getItemDescrip() {
        //String[] itemArray = Arrays.copyOfRange(getInputStringArray(),1,getInputStringArray().length);
        int indexItemName = getFullInput().indexOf("i/") + 2;
        return getFullInput().substring(indexItemName).trim();
    }


    /**
     * Attempts to create AddCommand object to be executed where it is called from
     *
     * @return AddCommand the command to be executed to add an item to the packing list, else
     *      an IncorrectCommand is created to be executed
     */
    public static Command createAddObj() {
        try {
            String itemName = getVariable("add");
            return new AddCommand(new Item(itemName));
        } catch (InvalidVariablesException e) {
            return new IncorrectCommand("Invalid Item Name",
                    "try to input a name (word(s)) to be added into the list");
        } catch (InvalidIndexException e) {
            return new IncorrectCommand("Invalid Item Index",
                    "try to input an integer number between 1 and " + PackingList.getItemList().size());
        }
    }

    /**
     * Attempts to create DeleteCommand object to be executed where it is called from
     *
     * @return DeleteCommand the command to be executed to delete an item to the packing list, else
     *      an IncorrectCommand is created to be executed
     */
    public static Command createDeleteObj() {
        try {
            String itemIndex = getVariable("delete");
            return new DeleteCommand(Integer.parseInt(itemIndex));
        } catch (InvalidVariablesException e) {
            return new IncorrectCommand("Invalid Item Name",
                    "Try to input a name (word(s)) to be added into the list");
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

    /**
     * Attempts to create PackCommand object to be executed where it is called from
     *
     * @return PackCommand the command to be executed to Pack an item in the packing list, else
     *      an IncorrectCommand is created to be executed
     */
    public static Command createPackObj() {
        try {
            String itemIndex = getVariable("pack");
            return new PackCommand(Integer.parseInt(itemIndex));
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
            String itemIndex = getVariable("unpack");
            return new UnpackCommand(Integer.parseInt(itemIndex));
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
