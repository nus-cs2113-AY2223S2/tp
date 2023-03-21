package utils.command;

import model.CardList;
import model.TagList;
import utils.UserInterface;
import utils.exceptions.InkaException;
import utils.storage.IDataStorage;

public class PrintHelpCommand extends Command {

    private String helpMessage;

    public PrintHelpCommand(String helpMessage) {
        this.helpMessage = helpMessage;
    }

    @Override
    public void execute(CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        ui.printHelp(helpMessage);
    }
}
