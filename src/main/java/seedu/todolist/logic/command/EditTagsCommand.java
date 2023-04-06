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
import java.util.StringJoiner;
import java.util.TreeSet;

public class EditTagsCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_TAGS, Flags.EDIT, Flags.EDIT_DELETE};

    private HashSet<Integer> idHashSet;
    private TreeSet<String> tags;
    private Flags purpose = Flags.EDIT;

    public EditTagsCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_TAGS));
        if (args.containsKey(Flags.EDIT)) {
            tags = ParserUtil.parseTags(args.get(Flags.EDIT));
        } else if (args.containsKey(Flags.EDIT_DELETE)) {
            tags = ParserUtil.parseTags(args.get(Flags.EDIT_DELETE));
            purpose = Flags.EDIT_DELETE;
        } else {
            throw new InvalidEditException();
        }

    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException, InvalidEditException {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        for (int id : idHashSet) {
            switch (purpose) {
            case EDIT_DELETE:
                String taskString = taskList.removeTags(id, tags);
                stringJoiner.add(taskString);
                break;
            case EDIT:
                taskString = taskList.setTags(id, tags);
                stringJoiner.add(taskString);
                break;
            default:
                throw new InvalidEditException();
            }
        }
        switch (purpose) {
        case EDIT_DELETE:
            ui.printDeleteTagsMessage(FormatterUtil.getTagsAsString(tags), stringJoiner.toString());
            break;
        case EDIT:
            ui.printEditTaskMessage("tags", FormatterUtil.getTagsAsString(tags), stringJoiner.toString());
            break;
        default:
            throw new InvalidEditException();
        }
    }
}
