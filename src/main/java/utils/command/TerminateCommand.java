package utils.command;

import model.CardList;
import utils.UserInterface;
import utils.storage.IDataStorage;

public class TerminateCommand extends Command {
    @Override
    public void execute(CardList cardList, UserInterface ui, IDataStorage storage) {
        ui.printBye();
    }
}
