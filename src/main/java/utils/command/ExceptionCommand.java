package utils.command;

import model.CardList;
import model.DeckList;
import model.TagList;
import utils.UserInterface;
import utils.exceptions.InkaException;
import utils.storage.IDataStorage;

public class ExceptionCommand extends Command {
    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList,UserInterface ui, IDataStorage storage)
            throws InkaException {
        ui.printWrongCommand();
    }
}
