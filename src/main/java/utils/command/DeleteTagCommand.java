package utils.command;

import java.util.ArrayList;
import java.util.UUID;
import model.Card;
import model.CardList;
import model.Tag;
import model.TagList;
import utils.UserInterface;
import utils.storage.IDataStorage;

public class DeleteTagCommand extends Command{
    private String tagName;
    private UUID tagUUID;

    public DeleteTagCommand(String tagName) {
        this.tagName= tagName;
    }

    private void removeTagFromCards (CardList cardList, TagList tagList, UserInterface ui) {
        Tag tag = tagList.findTag(tagName);
        tagUUID = tag.getUUID();

        //for each card whose uuid is listed under the tag, remove the tag uuid from that card
        for (UUID cardUUID : tag.getCardsUUID()) {
            Card affectedCard = cardList.findCard(cardUUID.toString());
            affectedCard.removeTag(tagUUID);
            ui.printRemoveTagFromCard(affectedCard.getUuid() , tagUUID);
        }
    }
    @Override
    public void execute (CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage) {
        removeTagFromCards(cardList,tagList, ui);
        tagList.deleteTagByUUID(tagUUID);
        ui.printRemoveTagFromTagList (tagUUID);
    }
}
