package seedu.duke.command;

import seedu.duke.company.Company;
import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;

public class UnconfirmCommand extends Command {
    protected int companyNum;

    public UnconfirmCommand(String commandType, int companyNum){
        super(commandType);
        this.companyNum = companyNum;
    }
    @Override
    public void execute(CompanyList company){
        try {
            Company companyToConfirm = company.getCompany(companyNum);
            companyToConfirm.markUnconfirmed();

        } catch (InvalidIndexException err){
            System.out.println("Invalid index provided! Please try again");
        }
    }

}
