package utils.command;

import utils.Card;
import utils.cardlist.CardList;
import utils.userinterface.UserInterface;

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
