package utils.command;

import model.Card;
import model.CardList;
import utils.UserInterface;
import utils.storage.IStorage;

public class AddCommand extends Command {
    private final Card card;

    public AddCommand(Card card) {
        this.card = card;
    }

    @Override
    public void execute(CardList cardList, UserInterface ui, IStorage storage) {
        cardList.addCard(card);
        ui.printAddQuestionSuccess();
        ui.printNumOfQuestions(cardList);
    }
}
