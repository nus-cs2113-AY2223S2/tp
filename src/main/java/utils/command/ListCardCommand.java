package utils.command;

import model.CardList;
import model.TagList;
import utils.UserInterface;
import utils.storage.IDataStorage;

public class ListCardCommand extends Command {
    @Override
    public void execute(CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage) {
        ui.printCardList(cardList);
    }
}
