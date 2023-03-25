package seedu.duke.command;

import seedu.duke.company.Company;
import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.storage.CompanyListEncoder;

import java.io.IOException;

public class UnconfirmCommand extends Command {
    protected int companyNum;

    public UnconfirmCommand(String commandType, int companyNum){
        super(commandType);
        this.companyNum = companyNum;
    }
    @Override
    public void execute(CompanyList companyList){
        try {
            Company companyToConfirm = companyList.getCompany(companyNum);
            companyToConfirm.markUnconfirmed();
            CompanyListEncoder.write(companyList);
        } catch (InvalidIndexException | IOException e) {
            System.out.println("Invalid index provided! Please try again");
        }
    }

}
