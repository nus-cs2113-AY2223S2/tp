package utils.command;

import java.util.UUID;
import model.Card;
import model.CardList;
import model.Tag;
import model.TagList;
import utils.UserInterface;
import utils.storage.IDataStorage;

public class AddCardToTagCommand extends Command {
    private String tagName;
    private UUID tagUUID;
    private UUID cardUUID ;

    public AddCardToTagCommand(String tagName, String cardUUID) {
        this.tagName = tagName;
        this.cardUUID = UUID.fromString(cardUUID);
    }



    protected void AddCardToTag(TagList tagList, CardList cardList) {
        //find the corresponding Tag and Card based on its tagName and card uuid
        Tag tagToAdd = tagList.findTag(tagName);
        Card cardToAdd = cardList.findCard(cardUUID);

        if (cardToAdd ==null) {
            //means the card doesn't exist yet, throw exception
        }

        if (tagToAdd ==null) {
            System.out.println("Tag does not exist.. creating a new one");
            tagToAdd = new Tag(tagName,cardUUID);
            tagList.addTag(tagToAdd);
        } else {
            System.out.println("Tag already exists, adding tag to the card and vice versa");
            tagToAdd.addCard(cardUUID);
        }

        //add the tag uuid to the card
        tagUUID = tagToAdd.getUUID();
        cardToAdd.addTag(tagUUID);
    }

    @Override
    public void execute(CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage) {
        AddCardToTag(tagList, cardList);
        ui.printAddTagToCardSuccess(cardUUID, tagUUID);
    }
}
