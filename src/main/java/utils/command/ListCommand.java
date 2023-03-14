package utils.command;

import model.CardList;
import utils.UserInterface;

public class ListCommand extends Command {
    @Override
    public void execute(CardList cardList, UserInterface ui) {
        ui.printList(cardList);
    }
}
