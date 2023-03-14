package utils.command;

import model.CardList;
import utils.UserInterface;
import utils.storage.IStorage;

public class ExceptionCommand extends Command {
    @Override
    public void execute(CardList cardList, UserInterface ui, IStorage storage) {
        ui.printWrongCommand();
    }
}
