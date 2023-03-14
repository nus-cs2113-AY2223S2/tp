package seedu.clialgo.command;

import seedu.clialgo.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

import java.util.Objects;

public class HelpCommand extends Command {
    private String command;

    public HelpCommand() {
        command = null;
    }

    public HelpCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    @Override
    public void execute (TopicManager topicManager, Ui ui, FileManager fileManager) {
        if (command == null) {
            ui.printHelpPage();
            return;
        }
        switch (command) {
        case "add":
            ui.printHelpAdd();
            return;
        case "remove":
            ui.printHelpRemove();
            return;
        case "filter":
            ui.printHelpFilter();
            return;
        case "list":
            ui.printHelpList();
            return;
        default:
            ui.printHelpExit();
        }
    }

    @Override
    public boolean equals(Command otherCommand) {
        HelpCommand otherHelpCommand = (HelpCommand) otherCommand;

        return Objects.equals(this.getCommand(), otherHelpCommand.getCommand());
    }
}
