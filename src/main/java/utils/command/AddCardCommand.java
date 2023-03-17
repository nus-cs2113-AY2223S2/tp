package utils.command;

import model.Card;
import model.CardList;
import model.TagList;
import utils.UserInterface;
import utils.storage.IDataStorage;

public class AddCardCommand extends Command {
    private final Card card;

    public AddCardCommand(Card card) {
        this.card = card;
    }

    @Override
    public void execute(CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage) {
        cardList.addCard(card);
        ui.printAddQuestionSuccess();
        ui.printNumOfQuestions(cardList);
    }
}
