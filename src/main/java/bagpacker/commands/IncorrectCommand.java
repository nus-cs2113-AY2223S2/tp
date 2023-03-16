package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.PackingList;

public class IncorrectCommand extends Command {
    private final String errorType;
    private final String helpMessage;
    public IncorrectCommand(String errorType, String helpMessage) {
        this.helpMessage = helpMessage;
        this.errorType = errorType;
    }

    @Override
    public void execute(PackingList packingList) {
        Ui.errorMessage(errorType, helpMessage);
    }
}
