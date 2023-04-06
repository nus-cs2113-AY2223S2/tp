package utils.command;
import java.util.UUID;
import model.CardList;
import model.Deck;
import model.DeckList;
import model.DeckUUID;
import model.Tag;
import model.TagList;
import model.TagUUID;
import utils.UserInterface;
import utils.exceptions.CreateTagBeforeAddingToDeck;
import utils.exceptions.InkaException;
import utils.exceptions.TagInDeckException;
import utils.exceptions.UUIDWrongFormatException;
import utils.storage.IDataStorage;

public class AddTagToDeckCommand extends Command {
    private String deckName;
    private DeckUUID deckUUID;
    private TagUUID tagUUID;

    public AddTagToDeckCommand(String deckName, String tagUUID) throws InkaException {
        this.deckName = deckName;
        //this.tagName = tagName;
        try {
            this.tagUUID = new TagUUID(UUID.fromString(tagUUID));
        } catch (IllegalArgumentException e) {
            throw new UUIDWrongFormatException();
        }
    }

    private void addTagToDeck(DeckList deckList, TagList tagList, UserInterface ui) throws InkaException {
        //find the corresponding Deck and Card based on its deckName and card uuid
        Tag tagToAdd = tagList.findTagFromUUID(tagUUID);
        if(tagToAdd==null) {
            throw new CreateTagBeforeAddingToDeck();
        }

        Deck deckToAdd = deckList.findDeckFromName(deckName);
        if (deckToAdd == null) {
            ui.printDeckCreationSuccess();
            deckToAdd = new Deck(deckName, tagUUID);
            //Add the cards from UUID here
            deckList.addDeck(deckToAdd);
        } else if(deckToAdd.tagIsInDeck(tagUUID)) {
            throw new TagInDeckException();
        } else {
            deckToAdd.addTag(tagUUID);
        }
        deckToAdd.addCardstoMap(tagUUID, tagList); // add tagged cards to set
        deckToAdd.addCardsToSet(tagUUID, tagList); // add tagged cards to map
        deckUUID = deckToAdd.getDeckUUID();
        tagToAdd.addDeck(deckUUID);
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList,UserInterface ui, IDataStorage storage)
            throws InkaException {
        addTagToDeck(deckList, tagList, ui);
        ui.printAddTagToDeckSuccess(tagUUID, deckUUID);
    }
}
