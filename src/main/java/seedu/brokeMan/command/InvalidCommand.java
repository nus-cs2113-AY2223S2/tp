package seedu.brokeMan.command;

import seedu.brokeMan.ui.Ui;

import java.util.ArrayList;

public class InvalidCommand extends Command {
    private ArrayList<String> invalidMessages = new ArrayList<>();

    public InvalidCommand(String ... invalidMessages) {
        for (String message : invalidMessages) {
            this.invalidMessages.add(message);
        }
    }

    public void execute() {
        for (int i = 0; i < invalidMessages.size() - 1; i++) {
            Ui.showToUser(invalidMessages.get(i), "");
        }
        Ui.showToUserWithLineBreak(invalidMessages.get(invalidMessages.size() - 1), "");
    }
}
