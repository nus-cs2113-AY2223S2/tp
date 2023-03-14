package utils.command;

import model.CardList;
import utils.UserInterface;
import utils.storage.IDataStorage;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(CardList cardList, UserInterface ui, IDataStorage storage) {
        ui.printDeleteSuccess();
        // if the input is delete 1, this will delete the first element of the array which is element 0.
        cardList.delete(this.index - 1);
        ui.printNumOfQuestions(cardList);
    }
}
