package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.PackingList;
/**
 * HelpCommand class is used print a help message to users
 */
public class HelpCommand extends Command {
    /**
     * Calls the Ui class to print helpMessage
     *
     * @param packingList list containing all items
     */
    @Override
    public void execute(PackingList packingList) {
        Ui.helpMessage();
    }
}
