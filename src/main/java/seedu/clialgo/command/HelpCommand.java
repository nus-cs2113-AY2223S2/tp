package seedu.clialgo.command;

import seedu.clialgo.Buffer;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

import java.util.Objects;

//@@author nikkiDEEE
/**
 * Represents an executable command. A <code>HelpCommand</code> object is created whenever the
 * user wants assistance on what commands are supported by the program.
 */
public class HelpCommand extends Command {
    /** Command that the user need help with. */
    private final String command;

    public HelpCommand() {
        command = null;
    }

    public HelpCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    /**
     * Prints the help page.
     * If there is no <code>COMMAND_TYPE</code> present in the user input, it prints a general help page.
     * If there is a <code>COMMAND_TYPE</code> present in the user input, the specific help page based on the
     * <code>COMMAND_TYPE</code> (after <code>c/</code> delimiter) is printed.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     * @param buffer The <code>Buffer</code> object responsible for exporting filtered files.
     */
    @Override
    public void execute(TopicManager topicManager, Ui ui, FileManager fileManager, Buffer buffer) {
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
        case "topo":
            ui.printHelpTopoSort();
            return;
        case "export":
            ui.printHelpExport();
            return;
        case "list":
            ui.printHelpList();
            return;
        case "exit":
            ui.printHelpExit();
            return;
        default:
            ui.printHelpPrompt();
        }
    }

    /**
     * An overridden method that checks for equality of <code>HelpCommand </code> objects.
     *
     * @param otherCommand The other <code>HelpCommand </code> object to be checked against.
     * @return A boolean value to determine whether the <code>HelpCommand </code> objects are equal.
     */
    @Override
    public boolean equals(Command otherCommand) {
        HelpCommand otherHelpCommand = (HelpCommand) otherCommand;

        return Objects.equals(this.getCommand(), otherHelpCommand.getCommand());
    }
}
