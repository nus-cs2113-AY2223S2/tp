package utils.command;

import model.CardList;
import model.TagList;
import utils.UserInterface;
import utils.exceptions.InkaException;
import utils.storage.IDataStorage;

public abstract class Command {
    public abstract void execute(CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage)
            throws InkaException;
}
