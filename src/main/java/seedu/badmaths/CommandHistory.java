//@@author WilsonLee2000

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
