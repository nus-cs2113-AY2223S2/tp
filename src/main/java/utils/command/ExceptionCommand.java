package utils.command;

import model.CardList;
import utils.UserInterface;

public class ExceptionCommand extends Command {
    @Override
    public void execute(CardList cardList, UserInterface ui) {
        ui.printWrongCommand();
    }
}
