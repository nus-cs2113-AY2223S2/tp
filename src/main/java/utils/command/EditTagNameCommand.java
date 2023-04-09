package utils.command;

import model.CardList;
import model.DeckList;
import model.Tag;
import model.TagList;
import utils.UserInterface;
import utils.exceptions.InkaException;
import utils.exceptions.LongTagNameException;
import utils.exceptions.TagNotFoundException;
import utils.storage.IDataStorage;

public class EditTagNameCommand extends Command {
    String oldTagName;
    String newTagName;

    public EditTagNameCommand(String oldTagName, String newTagName) {
        this.oldTagName = oldTagName;
        this.newTagName = newTagName;
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {

        if (oldTagName.length() > 50 || newTagName.length() > 50) {
            throw new LongTagNameException();
        }

        Tag tag = tagList.findTagFromName(oldTagName);
        if (tag == null) {
            throw new TagNotFoundException();
        }

        tag.editTagName(newTagName);
        ui.printEditTagNameSuccess(oldTagName, tag);
    }
}
