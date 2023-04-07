package utils.command;

import java.util.ArrayList;
import model.Card;
import model.CardList;
import model.CardSelector;
import model.Deck;
import model.DeckList;
import model.DeckUUID;
import model.Tag;
import model.TagList;
import model.TagUUID;
import utils.UserInterface;
import utils.exceptions.CardNotFoundException;
import utils.exceptions.InkaException;
import utils.storage.IDataStorage;

public class ViewCardCommand extends Command {
    private CardSelector cardSelector;

    public ViewCardCommand(CardSelector cardSelector) {
        this.cardSelector = cardSelector;
    }

    /**
     * Find all the tags from the tagList whose UUID is specified in the uuids ArrayList.
     *
     * @param uuids   The ArrayList containing the UUID of the tag that is to be searched for.
     * @param tagList The tagList from which to look for the tags.
     * @return The ArrayList consisting the tags that have been found based on the UUID specified.
     */
    public TagList findTagsFromTagUUID(ArrayList<TagUUID> uuids, TagList tagList) {
        TagList tagsFound = new TagList();
        for (TagUUID uuid : uuids) {
            for (Tag tag : tagList.getTags()) {
                if (tag.getUUID().equals(uuid)) {
                    tagsFound.addTag(tag);
                }
            }
        }
        return tagsFound;
    }

    public DeckList findDecksFromDeckUUID(ArrayList<DeckUUID> uuids, DeckList deckList) {
        DeckList decksFound = new DeckList();
        for (DeckUUID uuid : uuids) {
            for (Deck deck : deckList.getDecks()) {
                if (deck.getDeckUUID().equals(uuid)) {
                    decksFound.addDeck(deck);
                }
            }
        }
        return decksFound;
    }

    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        Card cardToView = cardList.findCard(cardSelector);

        //find the card with the specified uuid, print the card, find all the tags under it, print all the tags
        if (cardToView != null) {
            ui.printCard(cardToView);

            ArrayList<TagUUID> tagsUUID = cardToView.getTagsUUID();
            ArrayList<DeckUUID> decksUUID = cardToView.getDecksUUID();

            TagList tagsFound = findTagsFromTagUUID(tagsUUID, tagList);
            DeckList decksFound = findDecksFromDeckUUID(decksUUID, deckList);
            ui.printTags(tagsFound);
            ui.printDecks(decksFound);
        } else {
            throw new CardNotFoundException();
        }
    }
}
