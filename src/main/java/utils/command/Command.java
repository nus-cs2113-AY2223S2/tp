package utils.command;

import utils.cardlist.CardList;
import utils.userinterface.UserInterface;

public abstract class Command {
    public abstract void execute(CardList cardList, UserInterface ui);
}
