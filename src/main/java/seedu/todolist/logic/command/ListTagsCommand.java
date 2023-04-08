package seedu.todolist.logic.command;

import seedu.todolist.logic.FormatterUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.TreeSet;

/**
 * Command class that will display all tags of Task objects in the given TaskList object.
 */
public class ListTagsCommand extends Command {
    /**
     * Displays all tags of tasks found in the given task list.
     *
     * @param taskList The task list to get tag information from.
     * @param ui The Ui object used to display the tags.
     */
    @Override
    public void execute(TaskList taskList, Config config, Ui ui) {
        TreeSet<String> tags = taskList.getAllTags();
        ui.printGetTagsMessage(tags.size(), FormatterUtil.getTagsAsString(tags));
    }
}
