package seedu.duke.command;


import seedu.duke.CompanyList;
import seedu.duke.EmptyListException;

public class ListCompanyCommand extends Command {
    public ListCompanyCommand(String commandType) {
        super(commandType);
    }

    @Override
    public void execute(CompanyList companyList) {
        try {
            companyList.printCompanyInformation();
        } catch (EmptyListException err) {
            System.out.println("Nothing inside company list");
        }
    }
}
