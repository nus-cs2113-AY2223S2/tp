package utils.command;

import java.util.ArrayList;
import java.util.UUID;
import model.Card;
import model.CardList;
import model.Tag;
import model.TagList;
import utils.UserInterface;
import utils.storage.IDataStorage;

public class ListCardsUnderTagCommand extends Command{
    private String tagName;

    public ListCardsUnderTagCommand(String tagName) {
        this.tagName = tagName;
    }

    protected CardList findCardsUnderTag(CardList cardList, TagList tagList) {
        Tag foundTag = tagList.findTag(tagName);
        if (foundTag == null) {
            //means the tag does not even exist to begin with , throw exception
        }

        ArrayList<UUID> cardsUUID = foundTag.getCardsUUID();
        CardList foundCardList = new CardList();

        for (Card card : cardList.getCards()) {
            if (cardsUUID.contains(card.getUuid())) {
                foundCardList.addCard(card);
            }
        }
        return foundCardList;
    }
    @Override
    public void execute(CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage) {
        CardList foundCardList = findCardsUnderTag(cardList,tagList);
        ui.printCardList(foundCardList);
    }
}
