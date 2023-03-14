package utils.command;

import model.Card;
import model.CardList;
import utils.UserInterface;
import utils.storage.IDataStorage;

public class AddCommand extends Command {
    private final Card card;

    public AddCommand(Card card) {
        this.card = card;
    }

    @Override
    public void execute(CardList cardList, UserInterface ui, IDataStorage storage) {
        cardList.addCard(card);
        ui.printAddQuestionSuccess();
        ui.printNumOfQuestions(cardList);
    }
}
