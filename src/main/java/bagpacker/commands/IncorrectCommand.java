package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.PackingList;

public class IncorrectCommand extends Command {
    public final String feedbackToUser;
    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public void execute(PackingList packingList) {
        Ui.printToUser(feedbackToUser);
    }
}
