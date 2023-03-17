package utils.command;

import model.CardList;
import model.TagList;
import utils.UserInterface;
import utils.storage.IDataStorage;

public class ListTagsCommand extends Command {
    @Override
    public void execute(CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage) {
        ui.printTagList(tagList);
    }
}
