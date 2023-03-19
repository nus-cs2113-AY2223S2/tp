package seedu.duke.command;


import seedu.duke.company.CompanyList;

public class DeleteCommand extends Command{
    protected int taskNum;
    public DeleteCommand(String commandType, int taskNum){
        super(commandType);
        this.taskNum = taskNum;
    }

    @Override
    public void execute(CompanyList companyList) {
        System.out.println("delete command");
    }
}
