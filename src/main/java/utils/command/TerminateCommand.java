package utils.command;

import model.CardList;
import utils.UserInterface;

public class TerminateCommand extends Command {
    @Override
    public void execute(CardList cardList, UserInterface ui) {
        ui.printBye();
    }
}
