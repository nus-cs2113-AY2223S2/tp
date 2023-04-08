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
import bagpacker.exception.InvalidQuantityException;
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

    /**
     * Sets the fullInput
     *
     * @param fullInput the fullInput to set
     */
    public static void setFullInput(String fullInput) {
        Parser.fullInput = fullInput;
    }

    /**
     * Returns the user input as in array format
     */
    public static ArrayList<String> getInputStringArray() {
        return inputStringArray;
    }

    /**
     * Sets the inputStringArray
     *
     * @param inputStringArray the inputStringArray to set
     */
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
     * {@link Command}
     *
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
     * Returns a string which represents the index of the item from the user input
     *
     * @return inputIndex which is the item index of the item in packing list
     * @throws InvalidIndexException when the item index is not valid
     */
    public static String getItemIndex() throws InvalidIndexException, InvalidVariablesException {
        String inputIndex;
        int itemIndex;
        if (inputStringArray.size() != 2) {
            throw new InvalidVariablesException();
        }
        try {
            inputIndex = inputStringArray.get(1);
            itemIndex = Integer.parseInt(inputIndex);
            if (itemIndex < 1 | itemIndex > PackingList.getItemList().size()) {
                throw new InvalidIndexException();
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidVariablesException();
        }
        return inputIndex;
    }

    /**
     * Gets the keyword required for {@link FindCommand} constructor
     *
     * @return keyword to search for in item name
     */
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
            if (PackingList.itemFinder(itemName)) {
                assert (PackingList.getItemByName(itemName) != null);
                if (1000000 - PackingList.getItemByName(itemName).getTotalQuantity() < itemQuantity) {
                    throw new InvalidQuantityException();
                }
            } else {
                if (itemQuantity < 1 | itemQuantity > 1000000) {
                    throw new InvalidQuantityException();
                }
            }
            return new AddCommand(new Item(itemQuantity, itemName));
        } catch (NumberFormatException | InvalidVariablesException | ArrayIndexOutOfBoundsException |
                 StringIndexOutOfBoundsException e) {
            return new IncorrectCommand("Invalid Add Command Input",
                    "How to use add command:\n" + PackCommand.HELP_MSG);
        } catch (InvalidQuantityException e) {
            return new IncorrectCommand("Invalid Item Quantity",
                    "Can only add a positive quantity, where the total quantity of an item is at most 1,000,000");
        }
    }

    /**
     * Returns the relevant components of an add command from the user input
     *
     * @return String array of quantity and itemName
     * @throws InvalidVariablesException       not enough variables or /of not found
     * @throws StringIndexOutOfBoundsException not enough variables
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
        if (PackingList.getItemList().size() == 0) {
            return new IncorrectCommand("Invalid Item Index",
                    "Your packing list is empty, there is nothing to delete");
        } else {
            try {
                String itemIndex = getItemIndex();
                return new DeleteCommand(Integer.parseInt(itemIndex));
            } catch (InvalidVariablesException e) {
                return new IncorrectCommand("Invalid Delete Command Input",
                        "How to use delete command:\n" + DeleteCommand.HELP_MSG);
            } catch (InvalidIndexException e) {
                return new IncorrectCommand("Invalid Item Index",
                        "Try to input a positive integer number that is at most " +
                                PackingList.getItemList().size());
            }
        }
    }

    /**
     * Attempts to create PackCommand object to be executed where it is called from
     *
     * @return PackCommand the command to be executed to Pack an item in the packing list, else an IncorrectCommand is
     *         created to be executed
     */
    public static Command createPackObj() {
        int quantityNotPacked;
        if (PackingList.getItemList().size() == 0) {
            return new IncorrectCommand("Empty Packing List",
                    "Your packing list is empty, there is nothing to pack");
        }
        try {
            String[] quantityAndIndex = getPackVariables();
            int itemQuantity = Integer.parseInt(quantityAndIndex[0]);
            int itemIndex = Integer.parseInt(quantityAndIndex[1]);
            quantityNotPacked = PackingList.get(itemIndex - 1).getUnpackedQuantity();
            if (quantityNotPacked == 0) {
                return new IncorrectCommand("Item Fully Packed",
                        "You are done packing this item");
            }
            if (itemQuantity < 1 | itemQuantity > quantityNotPacked | itemQuantity > 1000000) {
                throw new InvalidQuantityException();
            }
            if (itemIndex < 1 | itemIndex > PackingList.getItemList().size()) {
                throw new InvalidIndexException();
            }
            return new PackCommand(itemQuantity, itemIndex);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | InvalidVariablesException e) {
            return new IncorrectCommand("Invalid Pack Command Input",
                    "How to use pack command:\n" + PackCommand.HELP_MSG);
        } catch (InvalidIndexException e) {
            return new IncorrectCommand("Invalid Item Index",
                    "Try to input a positive integer number that is at most " +
                            PackingList.getItemList().size() + " (Max integer supported is 1,000,000)");
        } catch (InvalidQuantityException e) {
            return new IncorrectCommand("Invalid Item Quantity",
                    "Can only pack a positive quantity that is less than or equal to the unpacked quantity" +
                            " (Max integer supported is 1,000,000)");
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
        if (PackingList.getItemList().size() == 0) {
            return new IncorrectCommand("Empty Packing List",
                    "Your packing list is empty, there is nothing to edit");
        }
        int itemIndex;
        try {
            String[] quantityAndIndex = getEditQuantityVariables();
            int newTotalQuantity = Integer.parseInt(quantityAndIndex[0]);
            itemIndex = Integer.parseInt(quantityAndIndex[1]);
            int packedQuantity = PackingList.get(itemIndex - 1).getPackedQuantity();
            if (newTotalQuantity < 1 | newTotalQuantity > 1000000 | newTotalQuantity < packedQuantity) {
                throw new InvalidQuantityException();
            }
            if (itemIndex < 1 | itemIndex > PackingList.getItemList().size()) {
                throw new InvalidIndexException();
            }
            return new EditQuantityCommand(newTotalQuantity, itemIndex);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | InvalidVariablesException e) {
            return new IncorrectCommand("Invalid Editquantity Command Input",
                    "How to use editquantity command:\n" + EditQuantityCommand.HELP_MSG);
        } catch (InvalidIndexException | IndexOutOfBoundsException e) {
            return new IncorrectCommand("Invalid Item Index",
                    "Try to input a positive integer that is at most "
                            + PackingList.getItemList().size());
        } catch (InvalidQuantityException e) {
            return new IncorrectCommand("Invalid Item Quantity",
                    "Can only change the quantity to something greater than what is currently packed and " +
                            "less than 1,000,000");
        }
    }

    /**
     * Parses the input variables to be used by {@link EditQuantityCommand} when constructed in createEditQuantityObj
     *
     * @return inputVariables in a String[] where the 0th index represents the new quantity and 1st index represents the
     *         item index in the packing list
     * @throws InvalidVariablesException invalid number of variables
     */
    public static String[] getEditQuantityVariables() throws InvalidVariablesException, IndexOutOfBoundsException {
        String[] inputStringList = fullInput.trim().split(" ", 2);
        String[] inputVariables = inputStringList[1].trim().split("\\s+/of\\s+");
        if (inputVariables.length != 2) {
            throw new InvalidVariablesException();
        }
        return inputVariables;
    }

    /**
     * Parses the input variables to be used by {@link PackCommand} when constructed in createPackObj
     *
     * @return inputVariables in a String[] where the 0th index represents the additional quantity to pack and 1st index
     *         represents the item index in the packing list
     * @throws InvalidIndexException     invalid item index given
     * @throws InvalidVariablesException invalid number of variables
     */
    public static String[] getPackVariables() throws InvalidIndexException, InvalidVariablesException {
        String[] inputStringList = fullInput.trim().split(" ", 2);
        String[] inputVariables = inputStringList[1].trim().split("\\s+/of\\s+");
        if (Integer.parseInt(inputVariables[1]) < 1 | Integer.parseInt(inputVariables[1])
                > PackingList.getItemList().size()) {
            throw new InvalidIndexException();
        }
        if (inputVariables.length != 2) {
            throw new InvalidVariablesException();
        }
        return inputVariables;
    }

    /**
     * Parses the input variables to be used by {@link PackAllCommand} when constructed in createPackAllObj
     *
     * @return itemIndex of the item to set as fully packed
     * @throws InvalidIndexException if item index is not valid
     */
    public static int getPackAllIndex() throws InvalidIndexException, InvalidVariablesException {
        try {
            String inputString = fullInput.substring(fullInput.indexOf("packall") + 7).trim();
            int ofIndex = inputString.trim().indexOf("/of");
            String packAllIndex = inputString.substring(ofIndex + 3).trim();
            int itemIndex = Integer.parseInt(packAllIndex);
            if (itemIndex < 1 | itemIndex > PackingList.getItemList().size()) {
                throw new InvalidIndexException();
            }
            if (ofIndex != 0) {
                throw new InvalidVariablesException();
            }
            return itemIndex;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new InvalidVariablesException();
        }
    }

    /**
     * Attempts to create a new PackAllCommand object will check if packing list is empty, or if item index is positive
     * and within the range of number of items in packing list ELSE an IncorrectCommand is created to be executed
     *
     * @return PackAllCommand if valid PackAll user input is parsed in
     */
    public static Command createPackAllObj() {
        if (PackingList.getItemList().size() == 0) {
            return new IncorrectCommand("Invalid Item Index",
                    "Your packing list is empty, there is nothing to pack");
        } else {
            try {
                int itemIndex = getPackAllIndex();
                return new PackAllCommand(itemIndex);
            } catch (InvalidVariablesException | ArrayIndexOutOfBoundsException e) {
                return new IncorrectCommand("Invalid Packall Command Input",
                        "How to use packall command:\n" + PackAllCommand.HELP_MSG);
            } catch (InvalidIndexException e) {
                return new IncorrectCommand("Invalid Item Index",
                        "Try to input a positive integer number that is at most " +
                                PackingList.getItemList().size());
            }
        }
    }

    /**
     * Attempts to create UnpackCommand object to be executed where it is called from
     *
     * @return UnpackCommand the command to be executed to unpack an item in the packing list, ELSE an IncorrectCommand
     *         is created to be executed
     */
    public static Command createUnpackObj() {
        int quantityPacked;
        if (PackingList.getItemList().size() == 0) {
            return new IncorrectCommand("Empty Packing List",
                    "Your packing list is empty, there is nothing to pack");
        }
        try {
            String[] quantityAndIndex = getPackVariables();
            int itemQuantity = Integer.parseInt(quantityAndIndex[0]);
            int itemIndex = Integer.parseInt(quantityAndIndex[1]);
            quantityPacked = PackingList.get(itemIndex - 1).getPackedQuantity();
            if (quantityPacked == 0) {
                return new IncorrectCommand("Item Not Packed",
                        "You have not packed this item at all");
            }
            if (itemQuantity < 1 | itemQuantity > quantityPacked | itemQuantity > 1000000) {
                throw new InvalidQuantityException();
            }
            if (itemIndex > PackingList.getItemList().size() | itemIndex < 1) {
                throw new InvalidIndexException();
            }
            return new UnpackCommand(itemQuantity, itemIndex);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | InvalidVariablesException e) {
            return new IncorrectCommand("Invalid Unpack Command Input",
                    "How to use unpack command:\n" + UnpackCommand.HELP_MSG);
        } catch (InvalidIndexException e) {
            return new IncorrectCommand("Invalid Item Index",
                    "Try to input a positive integer number that is at most " +
                            PackingList.getItemList().size() + " (Max integer supported is 1,000,000)");
        } catch (InvalidQuantityException e) {
            return new IncorrectCommand("Invalid Item Quantity",
                    "Can only unpack a positive quantity that is less than or equal to the packed quantity" +
                            " (Max integer supported is 1,000,000)");
        }
    }

    /**
     * Attempts to create UnpackAllCommand object to be executed where it is called from
     *
     * @return UnpackAllCommand the command to be executed to unpack an item in the packing list, ELSE an
     *         IncorrectCommand is created to be executed
     */
    public static Command createUnpackAllObj() {

        if (PackingList.getItemList().size() == 0) {
            return new IncorrectCommand("Invalid Item Index",
                    "Your packing list is empty, there is nothing to pack");
        } else {
            try {
                int itemIndex = getPackAllIndex();
                return new UnpackAllCommand(itemIndex);
            } catch (InvalidVariablesException | ArrayIndexOutOfBoundsException e) {
                return new IncorrectCommand("Invalid Unpackall Command Input",
                        "How to use unpackall command:\n" + UnpackAllCommand.HELP_MSG);
            } catch (InvalidIndexException e) {
                return new IncorrectCommand("Invalid Item Index",
                        "Try to input a positive integer number that is at most " +
                                PackingList.getItemList().size());
            }
        }
    }


    /**
     * Attempts to create a FindCommand object with the given keyword, if keyword is black an IncorrectCommand is
     * created to be executed
     *
     * @return FindCommand OR IncorrectCommand
     */
    public static Command createFindObj() {
        try {
            return new FindCommand(getKeyword());
        } catch (InvalidVariablesException e) {
            return new IncorrectCommand("Blank keyword",
                    "Try to input a keyword to be searched in the list");
        }
    }

    /**
     * Create a ListCommand object
     *
     * @return ListCommand
     */
    public static Command createListObj() {
        return new ListCommand();
    }

    /**
     * Create a ListUnpackedCommand object
     *
     * @return ListUnpackedCommand
     */
    private static Command createListUnpackedObj() {
        return new ListUnpackedCommand();
    }

    /**
     * Create a DeleteListCommand object
     *
     * @return DeleteListCommand
     */
    public static Command createDeleteListObj() {
        return new DeleteListCommand();
    }

    /**
     * Create a HelpCommand object
     *
     * @return HelpCommand
     */
    private static Command createHelpObj() {
        return new HelpCommand();
    }

    /**
     * Create a ByeCommand object
     *
     * @return ByeCommand
     */
    private static Command createByeObj() {
        return new ByeCommand();
    }
}
