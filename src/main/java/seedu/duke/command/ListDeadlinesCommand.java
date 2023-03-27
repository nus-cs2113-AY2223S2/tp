package seedu.duke.command;

import seedu.duke.Deadline;

import java.util.ArrayList;

public class ListDeadlinesCommand extends Command {
    private ArrayList<Deadline> deadlines;

    public ListDeadlinesCommand(ArrayList<Deadline> deadlines) {
        this.deadlines = deadlines;
    }

    @Override
    public void execute() {
        ui.printDeadlinesList(deadlines);
    }
}
