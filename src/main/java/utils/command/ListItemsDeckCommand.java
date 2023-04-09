package utils.command;

import java.util.ArrayList;
import model.Card;
import model.CardList;
import model.CardUUID;
import model.Deck;
import model.DeckList;
import model.Tag;
import model.TagList;
import model.TagUUID;
import utils.UserInterface;
import utils.exceptions.DeckNotFoundException;
import utils.exceptions.InkaException;
import utils.storage.IDataStorage;

public class ListItemsDeckCommand extends Command{

    private String deckName;

    public ListItemsDeckCommand(String deckName) {
        this.deckName = deckName;
    }

    private CardList findCardsUnderDeck(CardList cardList, DeckList deckList) throws InkaException {
        Deck foundDeck = deckList.findDeckFromName(deckName);
        if(foundDeck==null) {
            throw new DeckNotFoundException();
        }
        ArrayList<CardUUID> cardsUUID = foundDeck.getCardsUUID();
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
    private TagList findTagsUnderDeck(TagList tagList, DeckList deckList) throws InkaException {
        Deck foundDeck = deckList.findDeckFromName(deckName);
        if(foundDeck==null) {
            throw new DeckNotFoundException();
        }
        ArrayList<TagUUID> tagUUIDS = foundDeck.getTagsUUID();
        TagList foundTagList = new TagList();

        for (Tag tag : tagList.getTags()) {
            for (TagUUID tagUUID : tagUUIDS) {
                if (tagUUID.equals(tag.getUUID())) {
                    foundTagList.addTag(tag);
                }
            }
        }
        return foundTagList;
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        CardList foundCardList = findCardsUnderDeck(cardList, deckList);
        ui.printCardList(foundCardList);
        TagList foundTagList = findTagsUnderDeck(tagList, deckList);
        ui.printTagList(foundTagList);
    }
}
