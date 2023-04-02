package utils.command;

import java.util.ArrayList;
import model.Card;
import model.CardList;
import model.CardSelector;
import model.DeckList;
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
    public ArrayList<Tag> findTagsFromTagUUID(ArrayList<TagUUID> uuids, TagList tagList) {
        ArrayList<Tag> tags = new ArrayList<>();
        for (TagUUID uuid : uuids) {
            for (Tag tag : tagList.getTags()) {
                if (tag.getUUID().equals(uuid)) {
                    tags.add(tag);
                }
            }
        }
        return tags;
    }

    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        Card card = cardList.findCard(cardSelector);
        ArrayList<Tag> tags;

        //find the card with the specified uuid, print the card, find all the tags under it, print all the tags
        if (card != null) {
            ui.printCard(card);
            ArrayList<TagUUID> tagsUUID = card.getTagsUUID();
            tags = findTagsFromTagUUID(tagsUUID, tagList);
        } else {
            throw new CardNotFoundException();
        }
    }
}
