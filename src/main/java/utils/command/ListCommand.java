package utils.command;

import model.CardList;
import utils.UserInterface;
import utils.storage.IStorage;

public class ListCommand extends Command {
    @Override
    public void execute(CardList cardList, UserInterface ui, IStorage storage) {
        ui.printList(cardList);
    }
}
