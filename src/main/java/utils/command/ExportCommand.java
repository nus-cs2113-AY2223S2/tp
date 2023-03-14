package utils.command;

import model.CardList;
import utils.UserInterface;
import utils.exceptions.StorageSaveFailure;
import utils.storage.IStorage;

public class ExportCommand extends Command {

    @Override
    public void execute(CardList cardList, UserInterface ui, IStorage storage) {
        try {
            storage.save(cardList);
            ui.printSaveSuccess();
        } catch (StorageSaveFailure e) {
            ui.printSaveFailure();
        }
    }
}
