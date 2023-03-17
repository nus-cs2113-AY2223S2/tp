package utils.command;

import model.CardList;
import utils.UserInterface;
import utils.storage.IDataStorage;

public class ListCardCommand extends Command {
    @Override
    public void execute(CardList cardList, UserInterface ui, IDataStorage storage) {
        ui.printList(cardList);
    }
}
