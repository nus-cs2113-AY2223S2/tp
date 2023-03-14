package utils.command;

import utils.Card;
import utils.cardlist.CardList;
import utils.userinterface.UserInterface;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(CardList cardList, UserInterface ui);
}