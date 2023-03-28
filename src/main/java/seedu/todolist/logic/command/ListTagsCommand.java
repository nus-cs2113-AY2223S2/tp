package seedu.todolist.logic.command;

import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.logic.FormatterUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashSet;

public class ListTagsCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        HashSet<String> tags = taskList.getAllTags();
        ui.printGetTagsMessage(tags.size(), FormatterUtil.getTagsAsString(tags));
    }
}
