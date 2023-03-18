package utils.command;

import java.util.UUID;
import model.Card;
import model.CardList;
import model.CardUUID;
import model.Tag;
import model.TagList;
import model.TagUUID;
import utils.UserInterface;
import utils.storage.IDataStorage;

public class AddCardToTagCommand extends Command {
    private String tagName;
    private TagUUID tagUUID;
    private CardUUID cardUUID;

    public AddCardToTagCommand(String tagName, String cardUUID) {
        this.tagName = tagName;
        this.cardUUID = new CardUUID(UUID.fromString(cardUUID));
    }

    private void addCardToTag(TagList tagList, CardList cardList, UserInterface ui) {
        //find the corresponding Tag and Card based on its tagName and card uuid
        Tag tagToAdd = tagList.findTagFromName(tagName);
        Card cardToAdd = cardList.findCardFromUUID(cardUUID);

        if (cardToAdd == null) {
            //means the card doesn't exist yet, throw exception
        }

        if (tagToAdd == null) {
            ui.printTagCreationSuccess();
            tagToAdd = new Tag(tagName, cardUUID);
            tagList.addTag(tagToAdd);
        } else {
            tagToAdd.addCard(cardUUID);
        }

        //add the tag uuid to the card
        tagUUID = tagToAdd.getUUID();
        System.out.println(cardToAdd);
        cardToAdd.addTag(tagUUID);
    }

    @Override
    public void execute(CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage) {
        addCardToTag(tagList, cardList, ui);
        ui.printAddTagToCardSuccess(cardUUID, tagUUID);
    }
}
