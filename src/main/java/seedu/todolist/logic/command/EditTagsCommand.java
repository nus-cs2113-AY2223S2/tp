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

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class EditTagsCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_TAGS, Flags.EDIT, Flags.EDIT_ADD,
            Flags.EDIT_DELETE, Flags.FILTER_DONE, Flags.FILTER_OVERDUE, Flags.DESCRIPTION, Flags.EMAIL,
            Flags.FILTER_BEFORE, Flags.FILTER_AFTER, Flags.REPEAT, Flags.TAG, Flags.PRIORITY};

    private HashSet<Integer> idHashSet;
    private TreeSet<String> tags = new TreeSet<>();
    Predicate<Task> predicate;
    private Flags purpose;

    public EditTagsCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_TAGS));
        if (!Collections.disjoint(args.keySet(), Flags.filterFlags)) {
            // At least one filter flag is present
            predicate = ParserUtil.parseFilter(args);
        }
        if (idHashSet.isEmpty() == (predicate == null)) {
            throw new InvalidSelectException();
        }
        int count = (int) Stream.of(args.containsKey(Flags.EDIT), args.containsKey(Flags.EDIT_ADD),
                args.containsKey(Flags.EDIT_DELETE)).filter(bool -> bool).count();
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
            taskString = predicate == null
                    ? taskList.setTags(idHashSet, tags) : taskList.setTags(predicate, tags);
        } else if (purpose.equals(Flags.EDIT_ADD)) {
            taskString = predicate == null
                    ? taskList.addTags(idHashSet, tags) : taskList.addTags(predicate, tags);
        } else {
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
