package seedu.duke.command;

import seedu.duke.exception.InvalidIndexException;
import seedu.duke.company.Company;
import seedu.duke.company.CompanyList;


public class ConfirmCommand extends Command {
    protected int companyNum;

    public ConfirmCommand(String commandType, int companyNum){
        super(commandType);
        this.companyNum = companyNum;
    }
    @Override
    public void execute(CompanyList company){
        try {
            Company companyToConfirm = company.getCompany(companyNum);
            companyToConfirm.markConfirmed();

        } catch (InvalidIndexException err){
            System.out.println("Invalid index provided! Please try again");
        }
    }


}
