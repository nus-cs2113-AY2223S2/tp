//@@author WilsonLee2000

/**
 * Takes in an ArrayList containing previous history commands that the user have entered so far
 * during the current programme run session.
 * Prints out a list of history commands from the ArrayList if function is called.
 *
 * @param historyCommand
 */


package seedu.badmaths;
import java.util.ArrayList;

public class CommandHistory {
    ArrayList<String> historyCommand = new ArrayList<>();

    public CommandHistory(ArrayList<String> historyCommand) {
        this.historyCommand = historyCommand;
    }

    public void storeCommand(String inputCommand) {
        historyCommand.add(inputCommand);
    }

    public void displayHistory() {
        System.out.println("Here are the list of commands that you have entered so far:");
        for (String command : historyCommand) {
            System.out.println(command);
        }
        System.out.println();
    }
}
