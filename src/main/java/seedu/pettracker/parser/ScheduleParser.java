package seedu.pettracker.parser;

import seedu.pettracker.commands.ScheduleCommand;
import seedu.pettracker.exceptions.IllegalArgException;

public class ScheduleParser implements ArgParser<ScheduleCommand> {
    final String ARG_MESSAGE = "Schedule command should not have arguments";
    @Override
    public ScheduleCommand parse(String commandArgs) throws IllegalArgException {
        if (!commandArgs.isEmpty()) {
            throw new IllegalArgException(ARG_MESSAGE);
        }
        return new ScheduleCommand();
    }
}
