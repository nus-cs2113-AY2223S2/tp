package seedu.duke.command;

import seedu.duke.exception.InvalidIndexException;
import seedu.duke.company.Company;
import seedu.duke.company.CompanyList;
import seedu.duke.storage.CompanyListEncoder;

import java.io.IOException;


public class ConfirmCommand extends Command {
    protected int companyNum;

    public ConfirmCommand(String commandType, int companyNum){
        super(commandType);
        this.companyNum = companyNum;
    }
    @Override
    public void execute(CompanyList companyList){
        try {
            Company companyToConfirm = companyList.getCompany(companyNum);
            companyToConfirm.markConfirmed();
            CompanyListEncoder.write(companyList);
        } catch (InvalidIndexException | IOException err) {
            System.out.println("Invalid index provided! Please try again");
        }
    }


}
