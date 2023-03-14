package utils.command;

import model.CardList;
import utils.UserInterface;

public abstract class Command {
    public abstract void execute(CardList cardList, UserInterface ui);
}
