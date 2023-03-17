package utils.command;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.Card;
import model.CardList;
import model.Tag;
import model.TagList;
import utils.UserInterface;
import utils.storage.IDataStorage;

public class ViewCardCommand extends Command {
    private UUID cardUUID;

    public ViewCardCommand(String cardUUID) {
        this.cardUUID = UUID.fromString(cardUUID);
    }

    public ArrayList<Tag>  findTagsFromTagUUID (ArrayList<UUID> uuids, TagList tagList) {
        ArrayList<Tag> tags = new ArrayList<>();
       for (UUID uuid : uuids) {
           for (Tag tag : tagList.getTags()) {
               if (tag.getUUID().equals(uuid)) {
                   tags.add(tag);
               }
           }
       }
       return tags;
    }

    public void execute (CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage) {
        List<Card> cards = cardList.getCards();
        ArrayList<Tag> tags;

        //find the card with the specified uuid, print the card, find all the tags under it, print all the tags
        for (Card card : cards) {
            if (card.getUuid().equals(cardUUID)) {
                ui.printCard(card);
                ArrayList<UUID> tagsUUID = card.getTagsUUID();
                tags = findTagsFromTagUUID(tagsUUID, tagList);
                ui.printTags(tags);
            }
        }
    }
}
