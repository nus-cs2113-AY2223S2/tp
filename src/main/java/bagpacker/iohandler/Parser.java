package bagpacker.iohandler;

import bagpacker.commands.Command;
import bagpacker.commands.AddCommand;
import bagpacker.commands.DeleteCommand;
import bagpacker.commands.ListCommand;
import bagpacker.commands.ListUnpackedCommand;
import bagpacker.commands.PackCommand;
import bagpacker.commands.UnpackCommand;
import bagpacker.commands.DeleteListCommand;
import bagpacker.commands.PackAllCommand;
import bagpacker.commands.EditQuantityCommand;
import bagpacker.commands.FindCommand;
import bagpacker.commands.ByeCommand;
import bagpacker.commands.IncorrectCommand;
import bagpacker.commands.HelpCommand;
import bagpacker.commands.UnpackAllCommand;
import bagpacker.exception.EmptyInputException;
import bagpacker.exception.InvalidIndexException;
import bagpacker.exception.InvalidVariablesException;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
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
     * Returns the user input as in array format
     */
    public static ArrayList<String> getInputStringArray() {
        return inputStringArray;
    }

    public static void setInputStringArray(String[] inputStringArray) {
        Parser.inputStringArray = new ArrayList<>(Arrays.asList(inputStringArray));
    }

    /**
     * Returns the user input in String format
     */
    public static String getFullInput() {
        return fullInput;
    }

    /**
     * Takes in user's input and parses it into the relevant commands and arguments to create an object of type Command:
     *      {@link Command}
     * @return command to execute in bagPacker
     */
    public static Command parse() {
        String inputLine = "";
        while (inputLine.isEmpty()) {
            try {
                inputLine = readLine();
            } catch (EmptyInputException e) {
                Ui.errorMessage("Empty input received",
                        "try to input a command, to view all commands input 'help'");
            } catch (NoSuchElementException e) {
                Ui.errorMessage("Unrecognised Input Element",
                        "BagPacker is unable to parse in that command, application force closed, " +
                                "new data not saved.");
                return new ByeCommand();
            }
        }
        setFullInput(inputLine);
        String[] inputStringList = inputLine.trim().split("\\s+");
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
        case "listunpacked":
            return createListUnpackedObj();
        case "help":
            return createHelpObj();
        case "deletelist":
            return createDeleteListObj();
        case "packall":
            return createPackAllObj();
        case "unpackall":
            return createUnpackAllObj();
        case "editquantity":
            return createEditQuantityObj();
        case "find":
            return createFindObj();
        case "bye":
            return createByeObj();
        default:
            return new IncorrectCommand("'" + Parser.getCommand() + "' is an invalid User Command",
                    "input 'help' to receive all valid commands");
        }
    }

    /**
     * Reads and returns the full user input from the command line interface - trims the leading and trailing white
     * spaces
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
        return getInputStringArray().get(0).toLowerCase();
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
            itemName = fullInput.substring(itemIndStart).trim();
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
            inputIndex = inputStringArray.get(1);
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
     * Returns a string which represents the relevant variable depending on the command - "add" will return the item
     * name - "delete", "pack", "unpack" will return item index
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
            } else if (command.equals("find")) {
                itemVariable = getKeyword();
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

    private static String getKeyword() throws InvalidVariablesException {
        String keyword;
        if (inputStringArray.size() <= 1) {
            throw new InvalidVariablesException();
        }
        try {
            int itemIndStart = fullInput.indexOf(" ") + 1;
            keyword = fullInput.substring(itemIndStart).trim();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidVariablesException();
        }
        return keyword;
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
     * @return AddCommand the command to be executed to add an item to the packing list, else an IncorrectCommand is
     *         created to be executed
     */
    public static Command createAddObj() {
        try {
            String[] quantityAndName = getAddVariables();
            int itemQuantity = Integer.parseInt(quantityAndName[0]);
            String itemName = quantityAndName[1];
            if (itemName == null) {
                throw new InvalidVariablesException();
            }
            if (itemQuantity < 1) {
                throw new InvalidIndexException();
            }
            return new AddCommand(new Item(itemQuantity, itemName));
        } catch (InvalidVariablesException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            return new IncorrectCommand("Invalid input format",
                    AddCommand.HELP_MSG);
        } catch (NumberFormatException | InvalidIndexException e) {
            return new IncorrectCommand("Invalid Item Quantity",
                    "For item quantity, try to input a positive integer number between 1 and 1000000");
        }
    }

    /**
     * Returns the relevant components of an add command from the user input
     * @return String array of quantity and itemName
     * @throws InvalidVariablesException not enough variables
     */
    public static String[] getAddVariables() throws InvalidVariablesException, StringIndexOutOfBoundsException {
        String[] inputVariables = new String[3];
        String inputWOCommand = fullInput.substring(3).trim();
        int ofIndex = inputWOCommand.indexOf("/of");
        inputVariables[0] = inputWOCommand.substring(0, ofIndex).trim();
        inputVariables[1] = inputWOCommand.substring(ofIndex + 3).trim();
        if (!inputWOCommand.contains("/of") | inputVariables[1].equals("")) {
            throw new InvalidVariablesException();
        }
        return inputVariables;
    }

    /**
     * Attempts to create DeleteCommand object to be executed where it is called from
     *
     * @return DeleteCommand the command to be executed to delete an item to the packing list, else an IncorrectCommand
     *         is created to be executed
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
                        "Try to input a positive integer number that is at most " +
                                PackingList.getItemList().size());
            }
        }
    }

    public static int[] getQuantityAndIndex() throws InvalidVariablesException, InvalidIndexException {
        int[] itemQuantityAndIndex = new int[2];
        try {
            itemQuantityAndIndex[0] = Integer.parseInt(inputStringArray.get(1).trim());
            itemQuantityAndIndex[1] = Integer.parseInt(inputStringArray.get(3).trim());
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
     * @return PackCommand the command to be executed to Pack an item in the packing list, else an IncorrectCommand is
     *         created to be executed
     */
    public static Command createPackObj() {
        int quantityNotPacked = 0;
        try {
            String[] quantityAndIndex = getPackVariables();
            int itemQuantity = Integer.parseInt(quantityAndIndex[0]);
            int itemIndex = Integer.parseInt(quantityAndIndex[1]);
            quantityNotPacked = PackingList.get(itemIndex - 1).getUnpackedQuantity();
            if (itemQuantity < 1 | itemQuantity > quantityNotPacked) {
                throw new InvalidVariablesException();
            }
            return new PackCommand(itemQuantity, itemIndex);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Invalid Integer detected",
                    "Pack only supports the use of positive integers of at most 1000000");
        } catch (InvalidIndexException e) {
            if (PackingList.getItemList().size() == 0) {
                return new IncorrectCommand("Invalid Item Index",
                        "Your packing list is empty, there is nothing to pack");
            } else {
                return new IncorrectCommand("Invalid Item Index",
                        "Try to input a positive integer number that is at most " +
                                PackingList.getItemList().size());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectCommand("Invalid Input Variables",
                    PackCommand.HELP_MSG);
        } catch (InvalidVariablesException e) {
            if (quantityNotPacked == 0) {
                return new IncorrectCommand("Invalid Pack Usage",
                        "This item is fully packed");
            }
            return new IncorrectCommand("Invalid Pack Quantity",
                    "Try to input a positive quantity that is at most "
                            + quantityNotPacked + " to be packed");
        }
    }

    /**
     * Attempts to create EditQuantityCommand object to be executed where it is called from Will check whether QUANTITY
     * and INDEX are positive integers Will check whether changing the total quantity will cause packed
     *
     * @return EditQuantityCommand the command to be executed to edit the total quantity of an item in the packing list,
     *         else an IncorrectCommand objected is created to be executed
     */
    public static Command createEditQuantityObj() {
        try {
            String[] quantityAndIndex = getEditQuantityVariables();
            int newTotalQuantity = Integer.parseInt(quantityAndIndex[0]);
            if (newTotalQuantity < 1 | newTotalQuantity > 1000000) {
                throw new InvalidVariablesException();
            }
            int index = Integer.parseInt(quantityAndIndex[1]);
            if (index < 1 || index > PackingList.getItemList().size()) {
                throw new InvalidIndexException();
            }
            int packedQuantity = PackingList.get(index - 1).getPackedQuantity();
            if (newTotalQuantity < packedQuantity) {
                throw new InvalidIndexException();
            }
            return new EditQuantityCommand(newTotalQuantity, index);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Invalid Integer detected",
                    "BagPacker only supports the use of positive integers of at most 1000000");
        } catch (InvalidIndexException e) {
            return new IncorrectCommand("Invalid item quantity or index",
                    "For QUANTITY, try to input a positive integer that is at least the quantity packed.\n"
                            + "For INDEX, try to input a positive integer that is at most "
                            + PackingList.getItemList().size());
        } catch (InvalidVariablesException e) {
            return new IncorrectCommand("Invalid Quantity detected",
                    "editquantity only supports the use of positive integers of at most 1000000");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectCommand("Missing or additional inputs",
                    "A valid quantity and index are required.");
        }
    }

    public static String[] getEditQuantityVariables() throws InvalidIndexException {
        String[] inputStringList = fullInput.trim().split(" ", 2);
        String[] inputVariables = inputStringList[1].trim().split("\\s+/of\\s+");
        if (inputVariables.length != 2) {
            throw new InvalidIndexException();
        }
        return inputVariables;
    }

    /**
     * Attempts to create PackCommand object to be executed where it is called from
     *
     * @return PackCommand the command to be executed to Pack an item in the packing list, else an IncorrectCommand is
     *         created to be executed
     */

    public static String[] getPackVariables() throws InvalidIndexException {
        String[] inputStringList = fullInput.trim().split(" ", 2);
        String[] inputVariables = inputStringList[1].trim().split("\\s+/of\\s+");
        if (Integer.parseInt(inputVariables[1]) < 1 | Integer.parseInt(inputVariables[1])
                > PackingList.getItemList().size()) {
            throw new InvalidIndexException();
        }
        return inputVariables;
    }

    public static int getPackAllIndex() throws InvalidIndexException {
        String[] inputStringList = fullInput.trim().split("/", 2);
        inputStringList = inputStringList[1].split(" ", 2);
        int itemIndex = Integer.parseInt(inputStringList[1]);

        if (itemIndex < 1 | itemIndex > PackingList.getItemList().size()) {
            throw new InvalidIndexException();
        }

        return itemIndex;
    }

    public static Command createPackAllObj() {

        try {
            int itemIndex = getPackAllIndex();

            return new PackAllCommand(itemIndex);
        } catch (NumberFormatException | InvalidIndexException e) {
            if (PackingList.getItemList().size() == 0) {
                return new IncorrectCommand("Invalid Item Index",
                        "Your packing list is empty, there is nothing to pack");
            } else {
                return new IncorrectCommand("Invalid Item Index",
                        "Try to input a positive integer number that is at most " +
                                PackingList.getItemList().size());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectCommand("Invalid Input Variables",
                    PackAllCommand.HELP_MSG);
        }
    }

    /**
     * Attempts to create UnpackCommand object to be executed where it is called from
     *
     * @return UnpackCommand the command to be executed to unpack an item in the packing list, else an IncorrectCommand
     *         is created to be executed
     */
    public static Command createUnpackObj() {
        int quantityPacked = 0;
        try {
            String[] quantityAndIndex = getPackVariables();
            int itemQuantity = Integer.parseInt(quantityAndIndex[0]);
            if (itemQuantity <= 0) {
                return new IncorrectCommand("Invalid Unpack Quantity", "Your item quantity should " +
                        "be greater than 0");
            }

            int itemIndex = Integer.parseInt(quantityAndIndex[1]);

            if (itemIndex > PackingList.getItemList().size()) {
                return new IncorrectCommand("Invalid Item Index",
                        "Try to input a positive integer number that does not exceed " +
                                PackingList.getItemList().size());
            }
            quantityPacked = PackingList.get(itemIndex - 1).getPackedQuantity();
            if (itemQuantity < 1 | itemQuantity > quantityPacked) {
                throw new InvalidVariablesException();
            }

            return new UnpackCommand(itemQuantity, itemIndex);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Invalid Integer detected",
                    "Unpack only supports the use of positive integers of at most 1000000");
        } catch (InvalidIndexException e) {
            if (PackingList.getItemList().size() == 0) {
                return new IncorrectCommand("Invalid Item Index",
                        "Your packing list is empty, there is nothing to unpack");
            } else {
                return new IncorrectCommand("Invalid Item Index",
                        "Try to input a positive item index number that is at most " +
                                PackingList.getItemList().size());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectCommand("Invalid Input Variables",
                    UnpackCommand.HELP_MSG);
        } catch (InvalidVariablesException e) {
            if (quantityPacked == 0) {
                return new IncorrectCommand("Invalid Unpack Usage",
                        "This item is not even packed yet");
            }
            return new IncorrectCommand("Invalid Unpack Quantity",
                    "Try to input a positive quantity that does not exceed " + quantityPacked);
        }
    }

    public static Command createUnpackAllObj() {
        int quantityPacked = 0;
        try {
            int itemIndex = getPackAllIndex();
            if (itemIndex > PackingList.getItemList().size()) {
                return new IncorrectCommand("Invalid Item Index",
                        "Try to input a positive integer number that does not exceed " +
                                PackingList.getItemList().size());
            }
            quantityPacked = PackingList.get(itemIndex - 1).getPackedQuantity();
            if (quantityPacked == 0) {
                throw new InvalidVariablesException();
            }

            return new UnpackAllCommand(itemIndex);

        } catch (NumberFormatException | InvalidIndexException e) {
            if (PackingList.getItemList().size() == 0) {
                return new IncorrectCommand("Invalid Item Index",
                        "Your packing list is empty, there is nothing to unpack");
            } else {
                return new IncorrectCommand("Invalid Item Index",
                        "Try to input a positive item index number that is at most " +
                                PackingList.getItemList().size());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectCommand("Invalid Input Variables",
                    UnpackAllCommand.HELP_MSG);
        } catch (InvalidVariablesException e) {
            if (quantityPacked == 0) {
                return new IncorrectCommand("Invalid Unpackall Usage",
                        "This item is not even packed yet");
            }
            return new IncorrectCommand("Invalid Input Quantity",
                    "Try to input a positive quantity that does not exceed " + quantityPacked);
        }
    }


    /**
     * Attempts to create a FindCommand object with the given keyword.
     *
     * @return FindCommand
     */
    public static Command createFindObj() {
        try {
            return new FindCommand(getKeyword());
        } catch (InvalidVariablesException e) {
            return new IncorrectCommand("Blank keyword",
                    "Try to input a keyword to be searched in the list");
        }
    }

    public static Command createListObj() {
        return new ListCommand();
    }

    private static Command createListUnpackedObj() {
        return new ListUnpackedCommand();
    }

    public static Command createDeleteListObj() {
        return new DeleteListCommand();
    }

    private static Command createHelpObj() {
        return new HelpCommand();
    }

    private static Command createByeObj() {
        return new ByeCommand();
    }
}
