package seedu.duke;

import seedu.duke.exceptions.EditErrorException;
import seedu.duke.exceptions.MissingParametersException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String ADD_REGEX = "n/(\\w+) upc/(\\d+) qty/(\\d+) p/(\\d+(?:\\.\\d+)?)";
    private static final Integer NAME_INDEX = 1;
    private static final Integer UPC_INDEX = 2;
    private static final Integer QTY_INDEX = 3;
    private static final Integer PRICE_INDEX = 4;
    private static Scanner in = new Scanner(System.in);
    private ArrayList<String> parsedInfo = new ArrayList<>();
    private String commandWord;
    private String commandInfo;

    public void mainParser() {
        String command = in.nextLine().trim();
        String[] splitCommand = command.split(" ", 2);
        this.commandWord = splitCommand[0];
        if (splitCommand.length > 1) {
            this.commandInfo = splitCommand[1];
        }
        switch (commandWord) {
        case "bye":
        case "exit": // Works but not sure if violates switch case standards
            Ui.printExitMessage();
            System.exit(0);
            break;
        case "add":
            parseAdd(commandInfo);
            break;
        case "edit":
            parseEdit(commandInfo);
            break;
        case "list":
            parseList();
            break;
        default:
            Ui.printUnknownCommand();
            break;
        }
    }

    public void parseAdd(String rawInput) {
        try {
            if (rawInput == null) {
                throw new MissingParametersException();
            }
            Pattern pattern = Pattern.compile(ADD_REGEX);
            Matcher matcher = pattern.matcher(commandInfo);
            if (matcher.matches()) {
                Item newItem = new Item(matcher.group(NAME_INDEX), matcher.group(UPC_INDEX), matcher.group(QTY_INDEX),
                        matcher.group(PRICE_INDEX));
                Inventory.addItem(newItem);
            } else {
                Ui.printInvalidAddCommand();
            }
        } catch (MissingParametersException e) {
            e.missingAddItemParameters();
        }
    }

    /**
     * Handles the "edit" command by making sure that formatting is correct, before passing the user inputs
     * to another function to handle the edits needed to be made.
     *
     * @param editingInstructions The string containing the user input.
     */
    public void parseEdit(String editingInstructions) {
        String[] editInfo = editingInstructions.split(" ");
        try {
            if (!editInfo[0].contains("upc/") || editInfo.length == 1) {
                throw new EditErrorException();
            }
            Inventory.editItem(editInfo);
        } catch (EditErrorException eee) {
            Ui.printInvalidEditCommand();
        }
    }

    /**Temporary List Method created by Kai Wen for Edit Function Testing.*/
    public void parseList() {
        Inventory.listAll();
    }
}
