// @@author leonghuenweng
package pocketpal.frontend.commands;

import pocketpal.backend.Backend;
import pocketpal.frontend.ui.UI;


public class HelpCommand extends Command {
    /**
     * Print the help command.
     *
     * @param ui      UI to output action result
     * @param backend Backend to process requests
     */


    // @@author kaceycsn

    private final String helpCommand;

    public HelpCommand(){
        helpCommand = null;
    }

    public HelpCommand(String command){
        this.helpCommand = command;
    }

    public String getCommand(){
        return this.helpCommand;
    }
    @Override
    public void execute(UI ui, Backend backend) {
        if (helpCommand == null) {
            ui.printHelpMenu();
            return;
        }

        switch (helpCommand){
        case "Add":
            ui.printHelpAdd();
            return;
        case "Delete":
            ui.printHelpDelete();
            return;
        case "Edit":
            ui.printHelpEdit();
            return;
        case "View":
            ui.printHelpView();
            return;
        case "Bye":
            ui.printHelpBye();
            return;
        case "Help":
            ui.printHelpHelp();
            return;
        default:
            ui.printHelpMenu();
        }
    }
}
