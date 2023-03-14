package seedu.commands;

import seedu.expenditure.ExpenditureList;

public class HelpCommand extends Command {
    // Edit file accordingly
    public static final String COMMAND_WORD = "help";

    public HelpCommand() {
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult(
                "Here are the list of commands and their respective functions. "
                        + "Please take note of the FORMAT that is stated below\n"
                        +
                        "1. add: Add an expenditure to the record\n" +
                        "Format: add d/DATE a/AMOUNT c/CATEGORY\n" +
                        "2. delete: Deletes the specified expenditure from the record\n" +
                        "Format: delete INDEX\n" +
                        "3. edit: Edits an existing expenditure in the record\n" +
                        "Format: edit INDEX d/DATE a/AMOUNT c/CATEGORY\n" +
                        "4. list: Shows a list of expenditures and loans in the record based on existing categories\n" +
                        "Format: list /TYPE\n" +
                        "5. borrow: Keep a record of an incoming loan\n" +
                        "Format: borrow a/AMOUNT n/LENDER_NAME d/BORROWED_DATE\n" +
                        "6. lend: Add an expenditure to the record\n" +
                        "Format: lend a/AMOUNT n/BORROWER_NAME d/LENT_DATE");
    }
}
