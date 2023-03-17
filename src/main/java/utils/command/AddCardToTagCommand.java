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
    private String cardUUID ;

    public AddCardToTagCommand(String tagName, String cardUUID) {
        this.tagName = tagName;
        this.cardUUID = cardUUID;
    }



    protected Tag AddCardToTag(TagList tagList, CardList cardList) {
        //find the corresponding Tag and Card based on its tagName and card uuid
        Tag tagToAdd = tagList.findTag(tagName);
        Card cardToAdd = cardList.findCard(cardUUID);

        if (cardToAdd ==null) {
            //throw exceptions
        }

        if (tagToAdd ==null) {
            System.out.println("Tag does not exist.. creating a new one");
            tagToAdd = new Tag(tagName,cardUUID);
            tagList.addTag(tagToAdd);
        } else {
            System.out.println("Tag already exists, adding card too the tag");
            tagToAdd.addCard(UUID.fromString(cardUUID));
        }

        //add the tag uuid to the card
        String tagUUID = tagToAdd.getUUID();
        cardToAdd.addTag(UUID.fromString(tagUUID));
        return tagToAdd;
    }

    @Override
    public void execute(CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage) {
        Tag tagToAdd = AddCardToTag(tagList, cardList);
        ui.printAddQuestionSuccess();
        ui.printNumOfQuestions(cardList);
    }
}
