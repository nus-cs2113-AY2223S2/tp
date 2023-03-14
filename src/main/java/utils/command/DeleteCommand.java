package utils.command;

import utils.Card;
import utils.cardlist.CardList;
import utils.userinterface.UserInterface;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(CardList cardList, UserInterface ui) {
        ui.printDeleteSuccess();
        //ui.printCard(cardList, index);
        cardList.delete(this.index-1); // if the input is delete 1, this will delete the first element of the array which is element 0.
        ui.printNumOfQuestions(cardList);
    }
}