package seedu.todolist.logic.command;

import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.logic.FormatterUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.TreeSet;

public class ListTagsCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        TreeSet<String> tags = taskList.getAllTags();
        ui.printGetTagsMessage(tags.size(), FormatterUtil.getTagsAsString(tags));
    }
}
