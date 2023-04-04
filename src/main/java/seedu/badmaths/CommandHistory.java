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
        for (String command : historyCommand) {
            System.out.println(command);
        }

    }
}
