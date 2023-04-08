package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.PackingList;
/**
 * IncorrectCommand class is used to signal an incorrect command being typed into the CLI - it is a form of error
 *      handling
 */
public class IncorrectCommand extends Command {
    private final String errorType;
    private final String helpMessage;
    /**
     * Constructor for IncorrectCommand
     * @param errorType the name of the type of error
     * @param helpMessage the message to hint to the user as to how to avoid given error
     */
    public IncorrectCommand(String errorType, String helpMessage) {
        this.helpMessage = helpMessage;
        this.errorType = errorType;
    }
    /**
     * Calls the Ui class to print errorMessage
     *
     * @param packingList list containing all items
     */
    @Override
    public void execute(PackingList packingList) {
        Ui.errorMessage(errorType, helpMessage);
    }
}
