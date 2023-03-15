package bagpacker.iohandler;

import bagpacker.commands.*;
import bagpacker.exception.EmptyInputException;
import bagpacker.packingfunc.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Parser class contains methods to manipulate user input
 */
public class Parser {
    private static ArrayList<String> inputStringArray;
    private static String fullInput;
    private static String itemDescrip;
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

    public static Command parse() {
        String inputLine = "";
        while(inputLine.isEmpty()) {
            try {
                inputLine = readLine();
            } catch (EmptyInputException e) {
                Ui.errorMessage("Empty input received", "try to input a command, to view all commands input 'help'");
            }
        }
        setFullInput(inputLine);
        String[] inputStringList = inputLine.trim().split(" ");
        setInputStringArray(inputStringList);


        switch (inputStringList[0]) {
        case "add":
            //Add add function
            return addItem(inputStringList[1]);
        case "remove":
            return removeItem(inputStringList[1]);
        case "pack":
            return packItem(inputStringList[1]);
        case "unpack":
            return unpackItem(inputStringList[1]);
        case "list":
            return listCommand();
        case "help":
            return helpCommand();
        case "delete":
            return deleteList();
        default:
            return new IncorrectCommand("'" + Parser.getCommand() + "' is an invalid User Command\n" +
                    "input 'help' to receive all valid commands");
        }
    }

    private static String readLine() throws EmptyInputException {
        String inputLine;
        Scanner in = new Scanner(System.in);
        inputLine = in.nextLine().trim();
        if(inputLine.isEmpty()){
            throw new EmptyInputException();
        }
        return inputLine;
    }

    /**
     * Returns the user command in lower case
     */
    public static String getCommand() {
        return getInputStringArray().get(0).toLowerCase();
    }

    public static String getArgs() {
        return getInputStringArray().get(1);
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
     * Calls the AddCommand.execute() method to add an item to the packing list
     */
    public static Command addItem(String itemDescrip) {
        // Todo try, catch exception handling
        return new AddCommand(new Item(itemDescrip));
    }

    /**
     * Calls the DeleteCommand.execute() method to add an item to the packing list
     */
    public static Command removeItem(String itemDescrip) {
        return new DeleteCommand(Integer.parseInt(itemDescrip));
    }

    /**
     * Calls the PackCommand.execute() method to mark an item as packed in the packing list
     */
    public static Command packItem(String itemDescrip) {
        return new PackCommand(Integer.parseInt(itemDescrip));
    }

    /**
     * Calls the UnpackCommand.execute() method to mark an item as unpacked in the packing list
     */
    public static Command unpackItem(String itemDescrip) {
        return new UnpackCommand(Integer.parseInt(itemDescrip));
    }

    public static Command listCommand() {
        return new ListCommand();
    }

    public static Command deleteList() {
        return new DeleteListCommand();
    }

    private static Command helpCommand() {
        return new HelpCommand();
    }

}
