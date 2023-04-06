package utils.command;

import java.util.ArrayList;
import model.Card;
import model.CardList;
import model.CardUUID;
import model.DeckList;
import model.Tag;
import model.TagList;
import model.TagSelector;
import utils.UserInterface;
import utils.exceptions.InkaException;
import utils.exceptions.TagNotFoundException;
import utils.storage.IDataStorage;

public class ListCardsUnderTagCommand extends Command {
    private TagSelector tagSelector;

    public ListCardsUnderTagCommand(TagSelector tagSelector) {
        this.tagSelector = tagSelector;
    }

    /**
     * Finds all the cards that have been tagged with tagName tag.
     *
     * @param cardList The cardList from which to search for the cards.
     * @param tagList  The tagList that contains the specified tagName.
     * @return The ArrayList containing all the cards that has the tagName tag.
     * @throws InkaException
     */
    private CardList findCardsUnderTag(CardList cardList, TagList tagList) throws InkaException {
        Tag foundTag = tagList.findTag(tagSelector);
        if (foundTag == null) {
            throw new TagNotFoundException();
        }

        ArrayList<CardUUID> cardsUUID = foundTag.getCardsUUID();
        CardList foundCardList = new CardList();

        for (Card card : cardList.getCards()) {
            for (CardUUID cardUUID : cardsUUID) {
                if (cardUUID.equals(card.getUuid())) {
                    foundCardList.addCard(card);
                }
            }
        }
        return foundCardList;
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        CardList foundCardList = findCardsUnderTag(cardList, tagList);
        ui.printCardList(foundCardList);
    }
}
