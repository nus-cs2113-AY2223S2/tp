package bagpacker.iohandler;

import bagpacker.exception.EmptyInputException;
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
     * Returns the user input as in array list format
     */
    public static ArrayList<String> getInputStringArray() {
        return inputStringArray;
    }
    public static void setInputStringArray(ArrayList<String> inputStringArray) {
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
        setInputStringArray(new ArrayList<>(Arrays.asList(inputStringList)));
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
}
