package seedu.duke.command;

import seedu.duke.company.CompanyList;
import seedu.duke.exception.EmptyListException;
import seedu.duke.ui.Ui;

public class ListCompanyCommand extends Command {
    Ui ui = new Ui();
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
            ui.showLine();
            System.out.println("Nothing inside company list");
            ui.showLine();
        }
    }
}
