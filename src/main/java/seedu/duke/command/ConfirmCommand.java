package seedu.duke.command;

import seedu.duke.exception.InvalidIndexException;
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
            company.markConfirm(companyNum);
        } catch (InvalidIndexException err){
            System.out.println("Invalid index provided! Please try again");
        }
    }


}
