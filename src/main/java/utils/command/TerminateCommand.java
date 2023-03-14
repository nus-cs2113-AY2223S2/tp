package utils.command;

import utils.cardlist.CardList;
import utils.userinterface.UserInterface;

public class TerminateCommand extends Command {
    @Override
    public void execute(CardList cardList, UserInterface ui) {
        ui.printBye();
    }
}
