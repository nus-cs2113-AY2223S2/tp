package utils.command;

import model.Card;
import model.CardList;
import model.CardUUID;
import model.Tag;
import model.TagList;
import model.TagUUID;
import utils.UserInterface;
import utils.storage.IDataStorage;

public class DeleteTagCommand extends Command {
    private String tagName;
    private TagUUID tagUUID;

    public DeleteTagCommand(String tagName) {
        this.tagName = tagName;
    }

    private void removeTagFromCards(CardList cardList, TagList tagList, UserInterface ui) {
        Tag tag = tagList.findTagFromName(tagName);
        tagUUID = tag.getUUID();

        //for each card whose uuid is listed under the tag, remove the tag uuid from that card
        for (CardUUID cardUUID : tag.getCardsUUID()) {
            Card affectedCard = cardList.findCardFromUUID(cardUUID);
            affectedCard.removeTag(tagUUID);
            ui.printRemoveTagFromCard(affectedCard.getUuid(), tagUUID);
        }
    }

    @Override
    public void execute(CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage) {
        removeTagFromCards(cardList, tagList, ui);
        boolean isDeleted = tagList.deleteTagByUUID(tagUUID);
        // if isDeleted is false, it means tag doesn't exist in the first place, throw exception here
        ui.printRemoveTagFromTagList(tagUUID);
    }
}
