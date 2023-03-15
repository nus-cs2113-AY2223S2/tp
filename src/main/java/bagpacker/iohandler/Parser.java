package bagpacker.iohandler;

import bagpacker.commands.AddCommand;
import bagpacker.commands.DeleteCommand;
import bagpacker.commands.PackCommand;
import bagpacker.commands.UnpackCommand;
import bagpacker.commands.ListCommand;
import bagpacker.commands.DeleteListCommand;
import bagpacker.exception.EmptyInputException;
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
        return getInputStringArray().get(0).toLowerCase();
    }

<<<<<<< HEAD
    public static String getArgs() {
        return getInputStringArray().get(1);
=======
    /**
     * Returns the user item description
     */
    public static String getItemDescrip() {
        //String[] itemArray = Arrays.copyOfRange(getInputStringArray(),1,getInputStringArray().length);
        int indexItemName = getFullInput().indexOf("/i") + 2;
        String itemName = getFullInput().substring(indexItemName, getFullInput().length()).trim();
        return itemName;
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

    /**
     * Calls the PackCommand.execute() method to mark an item as packed in the packing list
     */

    public static void packItem(String itemDescrip, PackingList packingList) {

        for (int i = 0; i < packingList.size(); i++) {
            if (packingList.get(i).getItemName().equalsIgnoreCase(itemDescrip)) {
                PackCommand packCommand = new PackCommand(packingList.get(i));
                packCommand.execute(packingList);
            }

        }

    }

    /**
     * Calls the UnpackCommand.execute() method to mark an item as unpacked in the packing list
     */

    public static void unpackItem(String itemDescrip, PackingList packingList) {

        for (int i = 0; i < packingList.size(); i++) {
            if (packingList.get(i).getItemName().equalsIgnoreCase(itemDescrip)) {
                UnpackCommand unpackCommand = new UnpackCommand(packingList.get(i));
                unpackCommand.execute(packingList);
            }

        }

    }

    public static void displayList(PackingList packingList) {
        ListCommand listCommand = new ListCommand();
        listCommand.execute(packingList);
    }

    public static void deleteList(PackingList packingList) {
        DeleteListCommand deleteListCommand = new DeleteListCommand();
        deleteListCommand.execute(packingList);
>>>>>>> main/master
    }
}
