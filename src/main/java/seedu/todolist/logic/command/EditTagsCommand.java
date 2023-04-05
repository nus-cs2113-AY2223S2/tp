package seedu.todolist.logic.command;


import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.FormatterUtil;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class EditTagsCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_TAGS, Flags.EDIT, Flags.EDIT_DELETE};

    private HashSet<Integer> idHashSet;
    private TreeSet<String> tags;

    public EditTagsCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_TAGS));
        if (args.containsKey(Flags.EDIT)) {
            tags = ParserUtil.parseTags(args.get(Flags.EDIT));
        } else if (!args.containsKey(Flags.EDIT_DELETE)) {
            throw new InvalidEditException();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        for (int id : idHashSet) {
            String taskString = taskList.setTags(id, tags);
            if (tags == null) {
                ui.printEditDeleteTaskMessage("tags", taskString);
            } else {
                ui.printEditTaskMessage("tags", FormatterUtil.getTagsAsString(tags), taskString);
            }
        }
    }
}
