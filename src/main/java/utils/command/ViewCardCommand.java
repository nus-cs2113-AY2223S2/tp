package utils.command;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.Card;
import model.CardList;
import model.CardUUID;
import model.Tag;
import model.TagList;
import model.TagUUID;
import utils.UserInterface;
import utils.storage.IDataStorage;

public class ViewCardCommand extends Command {
    private CardUUID cardUUID;

    public ViewCardCommand(String cardUUID) {
        this.cardUUID = new CardUUID(UUID.fromString(cardUUID));
    }

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

    public void execute(CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage) {
        List<Card> cards = cardList.getCards();
        ArrayList<Tag> tags;

        //find the card with the specified uuid, print the card, find all the tags under it, print all the tags
        for (Card card : cards) {
            if (card.getUuid().equals(cardUUID)) {
                ui.printCard(card);
                ArrayList<TagUUID> tagsUUID = card.getTagsUUID();
                tags = findTagsFromTagUUID(tagsUUID, tagList);
                ui.printTags(tags);
            }
        }
    }
}
