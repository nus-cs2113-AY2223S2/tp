package bagpacker.iohandler;

import bagpacker.commands.AddCommand;
import bagpacker.commands.DeleteCommand;
import bagpacker.exception.EmptyInputException;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Parser class contains methods to manipulate user input
 */
public class Parser {
    private static String[] inputStringArray;
    private static String fullInput;
    public static void setFullInput(String fullInput) {
        Parser.fullInput = fullInput;
    }

    /**
     * Returns the user input as in array format
     */
    public static String[] getInputStringArray() {
        return inputStringArray;
    }
    public static void setInputStringArray(String[] inputStringArray) {
        Parser.inputStringArray = inputStringArray;
    }
    /**
     * Returns the user input in String format
     */
    public static String getFullInput() {
        return fullInput;
    }
    public static void receiveInput() {
        String inputLine = "";
        while(inputLine.isEmpty()){
            try {
                inputLine = readLine();
            } catch (EmptyInputException e) {
                Ui.errorMessage("Empty input received", "try to input a command, to view all commands input 'help'");
            }
        }
        setFullInput(inputLine);
        String[] inputStringList = inputLine.split(" ");
        setInputStringArray(inputStringList);

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
        return getInputStringArray()[0].toLowerCase();
    }

    /**
     * Returns the user item description
     */
    public static String getItemDescrip() {
        String[] itemArray = Arrays.copyOfRange(getInputStringArray(),1,getInputStringArray().length);

        return String.join(" ", itemArray);
    }

    /**
     * Calls the AddCommand.execute() method to add an item to the packing list
     */

    public static void addItem(String itemDescrip, PackingList packingList) {

        // Todo try, catch exception handling
        // Create item object
        Item item = new Item(itemDescrip,false);

        AddCommand addCommand = new AddCommand(item);

        addCommand.execute(packingList);

    }

    /**
     * Calls the DeleteCommand.execute() method to add an item to the packing list
     */

    public static void removeItem(String itemDescrip, PackingList packingList) {

        for (int i = 0; i < packingList.size(); i++) {
            if (packingList.get(i).getItemName().equalsIgnoreCase(itemDescrip)) {
                DeleteCommand deleteCommand = new DeleteCommand(packingList.get(i));
                deleteCommand.execute(packingList);
            }

        }

    }



}
