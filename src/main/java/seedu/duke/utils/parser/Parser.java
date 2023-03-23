package seedu.duke.utils.parser;

import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.SessionManager;
import seedu.duke.utils.Ui;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.EditCommand;
import seedu.duke.commands.FilterCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.RemoveCommand;
import seedu.duke.commands.SearchCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.EditErrorException;
import seedu.duke.exceptions.MissingParametersException;
import seedu.duke.exceptions.RemoveErrorException;
import seedu.duke.exceptions.SearchFilterErrorException;
import seedu.duke.types.Types;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String ADD_REGEX = "n/([\\w\\s]+) upc/(\\d+) qty/(\\d+) p/(\\d+(?:\\.\\d+)?)";
    private static final Integer NAME_INDEX = 1;
    private static final Integer UPC_INDEX = 2;
    private static final Integer QTY_INDEX = 3;
    private static final Integer PRICE_INDEX = 4;
    public static Scanner in = new Scanner(System.in);
    private Inventory inventory;
    private SessionManager session;


    public Parser(Inventory inventory, SessionManager currentSession) {
        this.inventory = inventory;
        this.session = currentSession;
    }

    public void mainParser() {
        String command = in.nextLine().trim();
        String[] splitCommand = command.split(" ", 2);
        String commandWord = splitCommand[0];
        String commandInfo;
        if (splitCommand.length > 1) {
            commandInfo = splitCommand[1];
        } else {
            commandInfo = "";
        }
        switch (commandWord) {
        case "bye":
        case "exit": // Works but not sure if violates switch case standards
            Ui.printExitMessage();
            System.exit(0);
            break;
        case "add":
            parseAdd(commandInfo, inventory);
            break;
        case "edit":
            parseEdit(commandInfo, inventory);
            break;
        case "list":
            parseList(inventory);
            break;
        case "search":
            parseSearch(commandInfo, inventory, Types.SearchType.KEYWORD);
            break;
        case "searchupc":
            parseSearchUPC(commandInfo, inventory, Types.SearchType.UPC);
            break;
        case "filter":
            parseFilter(commandInfo, inventory);
            break;
        case "remove":
            parseRemove(commandInfo, inventory);
            break;
        case "help":
            parseHelp();
            break;
        case "write":
            System.out.println("Writing to file...");
            session.writeSession(inventory);
            break;
        default:
            Ui.printUnknownCommand();
            break;
        }
        commandInfo = "";
        commandWord = "";
    }

    public void parseFilter(final String rawInput, Inventory inventory) {
        try {
            if (rawInput == null) {
                throw new MissingParametersException();
            }
            String[] commands = rawInput.split(" ");
            switch (commands[0]) {
            case "f/price":
                if (commands.length != 3) {
                    throw new MissingParametersException();
                }
                parseFilterPrice(commands, inventory);
                break;
            case "f/category":
            case "f/tag":
                if (commands.length < 2) {
                    throw new MissingParametersException();
                }
                parseFilterCategoryOrTag(commands, commands[0], inventory);
                break;
            default:
                throw new MissingParametersException();
            }
        } catch (MissingParametersException e) {
            e.missingSearchItemParameters();
        }
    }

    public void parseFilterPrice(String[] commands, Inventory inventory) {
        try {
            double price = Double.parseDouble(commands[2]);
            switch (commands[1]) {
            case "p/lt":
            case "p/gt":
            case "p/let":
            case "p/get":
                Command filterCommand = new FilterCommand(inventory, price, commands[1]);
                filterCommand.run();
                break;
            default:
                throw new SearchFilterErrorException();
            }
        } catch (SearchFilterErrorException e) {
            e.missingPriceComparator();
        } catch (NumberFormatException numberFormatException) {
            Ui.printDoubleNeeded();
        }
    }

    public void parseFilterCategoryOrTag(String[] commands, String mode, Inventory inventory) {
        String keyword = "";
        for (int i = 1; i < commands.length; i++) {
            keyword += commands[i];
            keyword += ' ';
        }
        keyword = keyword.trim();
        Command filterCommand = new FilterCommand(inventory, keyword, mode);
        filterCommand.run();
    }


    /**
     * Handles the "searchUPC" command by checking the validity of search term provided before passing to
     * the searchUPC function
     *
     * @param rawInput The user input string to be validated.
     */

    public void parseSearchUPC(String rawInput, Inventory inventory, Types.SearchType searchType) {
        try {
            if (rawInput == null) {
                throw new MissingParametersException();
            }
            if (rawInput.split(" ").length > 1) {
                throw new SearchFilterErrorException();
            }
            Command searchCommand = new SearchCommand(inventory, rawInput, searchType);
            searchCommand.run();
        } catch (MissingParametersException e) {
            e.missingSearchItemParameters();
        } catch (SearchFilterErrorException se) {
            Ui.printInvalidEditCommand();
        }
    }

    /**
     * Handles the "search" command by checking the validity of search term provided before passing to
     * the search function
     *
     * @param rawInput The user input string to be validated.
     */
    public void parseSearch(String rawInput, Inventory inventory, Types.SearchType searchType) {
        try {
            if (rawInput == null) {
                throw new MissingParametersException();
            }
            Command searchCommand = new SearchCommand(inventory, rawInput, searchType);
            searchCommand.run();
        } catch (MissingParametersException e) {
            e.missingSearchItemParameters();
        }
    }

    /**
     * Handles the "add" command by parsing the user's input into separate input parameters using regex
     *
     * @param rawInput The string to be parsed for adding of new item to the inventory.
     */
    public void parseAdd(String rawInput, Inventory inventory) {
        try {
            if (rawInput == null) {
                throw new MissingParametersException();
            }

            Pattern pattern = Pattern.compile(ADD_REGEX);
            Matcher matcher = pattern.matcher(rawInput);
            if (matcher.matches()) {
                Item newItem = new Item(matcher.group(NAME_INDEX), matcher.group(UPC_INDEX),
                        Integer.parseInt(matcher.group(QTY_INDEX)), Double.parseDouble(matcher.group(PRICE_INDEX)));
                Command addCommand = new AddCommand(inventory, newItem);
                addCommand.run();
            } else {
                Ui.printInvalidAddCommand();
            }
        } catch (MissingParametersException e) {
            e.missingAddItemParameters();
        }
    }

    public void parseList(Inventory inventory) {
        Command listCommand = new ListCommand(inventory);
        listCommand.run();
    }

    public void parseHelp() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.run();
    }


    /**
     * Handles the "edit" command by making sure that formatting is correct, before passing the user inputs
     * to another function to handle the edits needed to be made.
     *
     * @param editingInstructions The string containing the user input.
     * @param inventory           The inventory to be edited.
     */
    public void parseEdit(String editingInstructions, Inventory inventory) {
        String[] editInfo = editingInstructions.split(" ");
        //System.out.println(editingInstructions);
        //System.out.println(Arrays.toString(editInfo));
        try {
            if (!editInfo[0].contains("upc/") || editInfo.length == 1) {
                throw new EditErrorException();
            }
            assert editInfo[0].contains("upc/") : "UPC Code is not present in user command!";
            Command editCommand = new EditCommand(inventory, editInfo);
            editCommand.run();
        } catch (EditErrorException eee) {
            Ui.printInvalidEditCommand();
        }
    }

    /**
     * Handles the "remove" command by making sure that formatting is correct, before passing the user inputs
     *
     * @param rawInput  The string containing the user input.
     * @param inventory The inventory where item will be removed.
     */
    public void parseRemove(String rawInput, Inventory inventory) {
        // using f/item to remove using upc or f/index to remove using index of item in list
        try {
            if (rawInput == null) {
                throw new MissingParametersException();
            }
            String[] commands = rawInput.split(" ");
            switch (commands[0]) {
            case "f/item":
                parseRemoveByUpc(commands, inventory);
                break;
            case "f/index":
                parseRemoveByIndex(commands, inventory);
                break;
            default:
                throw new MissingParametersException();
            }
        } catch (MissingParametersException e) {
            e.missingRemoveItemParameters();
        } catch (RemoveErrorException e) {
            Ui.printInvalidUpc(inventory);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Ui.printInvalidIndex(inventory);
        }
    }

    private static void parseRemoveByIndex(final String[] commands, Inventory inventory)
            throws MissingParametersException {
        if (commands.length == 1) {
            throw new MissingParametersException();
        }
        Item itemToRemove;
        String confirmation;
        int itemIndex = Integer.parseInt(commands[1]);
        itemToRemove = inventory.getItemInventory().get(itemIndex);
        Ui.printConfirmMessage(itemToRemove);
        confirmation = in.nextLine();
        Command removeCommand = new RemoveCommand(inventory, itemIndex, confirmation);
        removeCommand.run();
    }

    private static void parseRemoveByUpc(final String[] commands, Inventory inventory)
            throws MissingParametersException, RemoveErrorException {
        String confirmation;
        Item itemToRemove;
        if (commands.length == 1 || !commands[1].startsWith("upc/")) {
            throw new MissingParametersException();
        }
        String upcCode = commands[1].replaceFirst("upc/", "");
        HashMap<String, Item> upcCodes = inventory.getUpcCodes();
        if (!upcCode.matches("(\\d+)") || !upcCodes.containsKey(upcCode)) {
            throw new RemoveErrorException();
        }
        itemToRemove = upcCodes.get(upcCode);
        Ui.printConfirmMessage(itemToRemove);
        confirmation = in.nextLine();
        Command removeCommand = new RemoveCommand(inventory, upcCode, confirmation);
        removeCommand.run();
    }
}
