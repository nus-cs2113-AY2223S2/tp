package utils.command;

import model.CardList;
import model.TagList;
import utils.UserInterface;
import utils.exceptions.InkaException;
import utils.storage.IDataStorage;

public class ListCardCommand extends Command {
    @Override
    public void execute(CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        ui.printCardList(cardList);
    }
}
