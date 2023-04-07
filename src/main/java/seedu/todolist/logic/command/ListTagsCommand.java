package seedu.todolist.logic.command;

import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.logic.FormatterUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.TreeSet;

public class ListTagsCommand extends Command {
    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws InvalidIdException {
        TreeSet<String> tags = taskList.getAllTags();
        ui.printGetTagsMessage(tags.size(), FormatterUtil.getTagsAsString(tags));
    }
}
