package seedu.duke.command;

import seedu.duke.company.CompanyList;
import seedu.duke.exception.EmptyListException;

public class ListCompanyCommand extends Command {
    public ListCompanyCommand(String commandType) {
        super(commandType);
    }

    /**
     * Prints out the list of companies to the user
     *
     * @param companyList which contains a list of all the companies
     * @throws EmptyListException if error occurred due to empty list
     */
    @Override
    public void execute(CompanyList companyList) {
        try {
            companyList.printCompanyInformation();
        } catch (EmptyListException err) {
            System.out.println("Nothing inside company list");
        }
    }
}
