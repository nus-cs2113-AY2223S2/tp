package utils.command;

import model.CardList;
import model.TagList;
import utils.UserInterface;
import utils.exceptions.InkaException;
import utils.exceptions.StorageSaveFailure;
import utils.storage.IDataStorage;

public class ExportCommand extends Command {

    @Override
    public void execute(CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        try {
            storage.save(cardList);
            ui.printSaveSuccess();
        } catch (StorageSaveFailure e) {
            ui.printSaveFailure();
        }
    }
}
