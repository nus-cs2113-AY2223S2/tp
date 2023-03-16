package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.PackingList;

public class HelpCommand extends Command {
    @Override
    public void execute(PackingList packingList) {
        Ui.helpMessage();
    }
}
