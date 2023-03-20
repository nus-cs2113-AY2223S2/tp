package utils.command;

import model.CardList;
import model.TagList;
import utils.UserInterface;
import utils.exceptions.UnknownItem;
import utils.exceptions.InkaException;
import utils.storage.IDataStorage;

public class DeleteCardCommand extends Command {

    private int index;

    public DeleteCardCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        try {
            // if the input is delete 1, this will delete the first element of the array which is element 0.
            cardList.delete(this.index - 1);
            ui.printDeleteSuccess();
            ui.printNumOfQuestions(cardList);
        } catch (IndexOutOfBoundsException e) {
            throw new UnknownItem();
        }
    }
}
