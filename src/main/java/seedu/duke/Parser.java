package seedu.duke;

import seedu.duke.exceptions.EditErrorException;
import seedu.duke.exceptions.MissingParametersException;
import seedu.duke.exceptions.SearchFilterErrorException;

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
        case "search":
            parseSearch(commandInfo);
            break;
        case "searchupc":
            parseSearchUPC(commandInfo);
            break;
        case "filter":
            parseFilter(commandInfo);
            break;
        default:
            Ui.printUnknownCommand();
            break;
        }
    }
    public void parseFilter(String rawInput){
        try{
            if(rawInput == null){
                throw new MissingParametersException();
            }
            String commands[] = rawInput.split(" ");
            switch(commands[0]){
            case "f/price":
                if(commands.length!=3){
                    throw new MissingParametersException();
                }
                parseFilterPrice(commands);
                break;
            case "f/category":
            case "f/tag":
                if(commands.length<2){
                    throw new MissingParametersException();
                }
                parseFilterCategoryOrTag(commands, commands[0]);
                break;
            default:
                throw new MissingParametersException();
            }
        }catch(MissingParametersException e){
            e.missingSearchItemParameters();
        }
    }
    public void parseFilterPrice(String commands[]){
        try{
            double price = Double.parseDouble(commands[2]);
            switch(commands[1]){
            case "p/lt":
            case "p/gt":
            case "p/let":
            case "p/get":
                Inventory.filterPrice(price, commands[1]);
                break;
            default:
                throw new SearchFilterErrorException();
            }
        }catch(SearchFilterErrorException e){
            e.missingPriceComparator();
        }catch(NumberFormatException numberFormatException){
            Ui.printDoubleNeeded();
        }
    }
    public void parseFilterCategoryOrTag(String commands[], String mode){
        String keyword = "";
        for(int i=1;i<commands.length;i++){
            keyword+=commands[i];
            keyword+=' ';
        }
        keyword.trim();
        if(mode.equals("f/category")){
            Inventory.filterCategory(keyword);
        }else{
            Inventory.filterTags(keyword);
        }
    }
    public void parseSearchUPC(String rawInput){
        try {
            if(rawInput == null){
                throw new MissingParametersException();
            }
            if(rawInput.split(" ").length>1){
                throw new SearchFilterErrorException();
            }
            Inventory.searchUPC(rawInput);
        }catch(MissingParametersException e){
            e.missingSearchItemParameters();
        }catch(SearchFilterErrorException se){
            Ui.printInvalidEditCommand();
        }
    }
    public void parseSearch(String rawInput){
        try {
            if (rawInput == null) {
                throw new MissingParametersException();
            }
            Inventory.search(rawInput);
        }catch(MissingParametersException e){
            e.missingSearchItemParameters();
        }
    }
    public void parseAdd(String rawInput) {
        try {
            if (rawInput == null) {
                throw new MissingParametersException();
            }
            Pattern pattern = Pattern.compile(ADD_REGEX);
            Matcher matcher = pattern.matcher(rawInput);
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

    /** Temporary List Method created by Kai Wen for Edit Function Testing.*/
    public void parseList() {
        Inventory.listAll();
    }
}
