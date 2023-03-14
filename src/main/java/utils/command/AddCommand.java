package utils.command;

import model.Card;
import model.CardList;
import utils.UserInterface;

public class AddCommand extends Command {
    private final Card card;

    public AddCommand(Card card) {
        this.card = card;
    }

    @Override
    public void execute(CardList cardList, UserInterface ui) {
        cardList.addCard(card);
        ui.printAddQuestionSuccess();
        ui.printNumOfQuestions(cardList);
    }
}
