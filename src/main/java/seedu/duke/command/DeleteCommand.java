package seedu.duke.command;


import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.storage.CompanyListEncoder;

import java.io.IOException;

public class DeleteCommand extends Command{
    protected int taskNum;
    public DeleteCommand(String commandType, int taskNum){
        super(commandType);
        this.taskNum = taskNum;
    }

    @Override
    public void execute(CompanyList companyList) {
        try {
            companyList.deleteCompanyInformation(taskNum);
            CompanyListEncoder.write(companyList);
        } catch (InvalidIndexException | IOException err) {
            System.out.println("Invalid index provided! Please try again");
        }
    }
}
