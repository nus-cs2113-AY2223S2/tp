package seedu.duke.command;

import seedu.duke.company.CompanyList;
import seedu.duke.exception.EmptyListException;

public class ListUnconfirmedCommand extends Command {
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
            System.out.println("Nothing inside company list");
        }
    }

}
