package seedu.duke.command;

import seedu.duke.company.CompanyList;
import seedu.duke.exception.EmptyListException;
import seedu.duke.ui.Ui;

public class ListUnconfirmedCommand extends Command {
    Ui ui = new Ui();
    public ListUnconfirmedCommand(String commandType) {
        super(commandType);
    }

    /**
     * Prints out the list of companies that have yet to confirm
     *
     * @param companyList which contains a list of all the companies
     * @throws EmptyListException if error occurred due to empty list
     */
    @Override
    public void execute(CompanyList companyList) {
        try {
            companyList.printUnconfirmed();
        } catch (EmptyListException err) {
            ui.showLine();
            System.out.println("Nothing inside company list");
            ui.showLine();
        }
    }

}
