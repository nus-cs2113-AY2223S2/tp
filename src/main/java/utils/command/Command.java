package utils.command;

import model.CardList;
import utils.UserInterface;
import utils.storage.IDataStorage;

public abstract class Command {
    public abstract void execute(CardList cardList, UserInterface ui, IDataStorage storage);
}
