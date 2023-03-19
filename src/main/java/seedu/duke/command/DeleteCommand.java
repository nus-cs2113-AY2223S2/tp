package seedu.duke.command;


import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;

public class DeleteCommand extends Command{
    protected int taskNum;
    public DeleteCommand(String commandType, int taskNum){
        super(commandType);
        this.taskNum = taskNum;
    }

    @Override
    public void execute(CompanyList companyList) {
        try{
            companyList.deleteCompanyInformation(taskNum);
        } catch (InvalidIndexException err){
            System.out.println("Invalid index provided! Please try again");
        }
    }
}
