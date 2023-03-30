package seedu.dukeofbooks.command;

import seedu.dukeofbooks.data.loan.LoanRecords;

public abstract class LoanCommand extends UserCommand {
    protected final LoanRecords loanRecords;

    public LoanCommand(LoanRecords loanRecords) {
        this.loanRecords = loanRecords;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult("This command is not executable!");
    }
}
