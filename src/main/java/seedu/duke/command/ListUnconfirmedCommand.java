package seedu.duke.command;

import seedu.duke.company.CompanyList;
import seedu.duke.exception.EmptyListException;
public class ListUnconfirmedCommand extends Command {
    public ListUnconfirmedCommand(String commandType) {
        super(commandType);
    }

    @Override
    public void execute(CompanyList companyList) {
        try {
            companyList.printUnconfirmed();
        } catch (EmptyListException err) {
            System.out.println("Nothing inside company list");
        }
    }

}
