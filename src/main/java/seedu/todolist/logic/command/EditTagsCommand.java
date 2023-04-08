package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.exception.InvalidSelectException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.FormatterUtil;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.Task;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.function.Predicate;

public class EditTagsCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_TAGS, Flags.EDIT, Flags.EDIT_ADD,
        Flags.EDIT_DELETE, Flags.DESCRIPTION, Flags.EMAIL, Flags.REPEAT, Flags.TAG, Flags.PRIORITY,
        Flags.FILTER_DONE, Flags.FILTER_OVERDUE, Flags.FILTER_BEFORE, Flags.FILTER_AFTER, Flags.FILTER_ALL};

    private TreeSet<String> tags;
    private HashSet<Integer> idHashSet;
    private Predicate<Task> predicate;
    private Flags purpose;

    public EditTagsCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_TAGS));
        predicate = ParserUtil.parseFilter(args);
        if (idHashSet.isEmpty() == (predicate == null)) {
            throw new InvalidSelectException();
        }
        int count = ParserUtil.countTrue(args.containsKey(Flags.EDIT), args.containsKey(Flags.EDIT_ADD),
                args.containsKey(Flags.EDIT_DELETE));
        if (count != 1) {
            // Can only have one of -add, -edit, -del
            throw new InvalidEditException(count);
        }
        if (args.containsKey(Flags.EDIT)) {
            tags = ParserUtil.parseTags(args.get(Flags.EDIT));
            purpose = Flags.EDIT;
        } else if (args.containsKey(Flags.EDIT_ADD)) {
            tags = ParserUtil.parseTags(args.get(Flags.EDIT_ADD));
            purpose = Flags.EDIT_ADD;
        } else {
            tags = ParserUtil.parseTags(args.get(Flags.EDIT_DELETE));
            purpose = Flags.EDIT_DELETE;
        }
        assert args.size() > 1: "Fewer arguments than expected!";
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws InvalidIdException {
        String taskString;
        if (purpose.equals(Flags.EDIT) || tags.isEmpty()) {
            // Set tags to given list, or delete all tags if list is empty
            taskString = predicate == null
                    ? taskList.setTags(idHashSet, tags) : taskList.setTags(predicate, tags);
        } else if (purpose.equals(Flags.EDIT_ADD)) {
            // Add all given tags
            taskString = predicate == null
                    ? taskList.addTags(idHashSet, tags) : taskList.addTags(predicate, tags);
        } else {
            // Delete all given tags
            taskString = predicate == null
                    ? taskList.removeTags(idHashSet, tags) : taskList.removeTags(predicate, tags);
        }

        if (taskString.isEmpty()) {
            ui.printFilteredNoTasksFoundMessage();
        } else if (tags.isEmpty()) {
            ui.printEditDeleteTaskMessage("tags", taskString);
        } else if (purpose.equals(Flags.EDIT)) {
            ui.printEditTaskMessage("tags", FormatterUtil.getTagsAsString(tags), taskString);
        } else if (purpose.equals(Flags.EDIT_ADD)) {
            ui.printEditTagsMessage("added", "to", FormatterUtil.getTagsAsString(tags), taskString);
        } else {
            ui.printEditTagsMessage("removed", "from", FormatterUtil.getTagsAsString(tags), taskString);
        }
    }
}
