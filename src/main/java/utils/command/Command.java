package utils.command;

import model.CardList;
import utils.UserInterface;
import utils.storage.IStorage;

public abstract class Command {
    public abstract void execute(CardList cardList, UserInterface ui, IStorage storage);
}
