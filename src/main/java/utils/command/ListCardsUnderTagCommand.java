package utils.command;

import java.util.ArrayList;
import java.util.Optional;
import model.Card;
import model.CardList;
import model.CardUUID;
import model.DeckList;
import model.Tag;
import model.TagList;
import model.TagSelector;
import utils.UserInterface;
import utils.exceptions.InkaException;
import utils.exceptions.LongTagNameException;
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
     * @param foundTag The tag under which to return all the cards from.
     * @return The ArrayList containing all the cards that has the tagName tag.
     * @throws InkaException
     */
    private CardList findCardsUnderTag(CardList cardList, Tag foundTag) throws InkaException {

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
        Tag foundTag = tagList.findTag(tagSelector);
        Optional<String> tagName = tagSelector.getTagName();

        if (tagName.get().length() > 50) {
            throw new LongTagNameException();
        } else if (foundTag == null) {
            throw new TagNotFoundException();
        }
        CardList foundCardList = findCardsUnderTag(cardList, foundTag);
        ui.printCardList(foundCardList);
    }
}
