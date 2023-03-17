package utils.command;

import model.CardList;
import model.Tag;
import model.TagList;
import utils.UserInterface;
import utils.storage.IDataStorage;

public class AddTagCommand extends Command {
    private final Tag tag;

    public AddTagCommand(Tag tag) {
        this.tag = tag;
    }

    @Override
    public void execute(CardList cardList, TagList tagList, UserInterface ui, IDataStorage storage) {
        tagList.addTag(tag);
        ui.printAddQuestionSuccess();
        ui.printNumOfQuestions(cardList);
    }
}
