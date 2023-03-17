package utils.tagcommand;

import model.TagList;
import utils.UserInterface;
import utils.storage.IDataStorage;

public abstract class Command {
    public abstract void execute(TagList tagList, UserInterface ui, IDataStorage storage);
}
