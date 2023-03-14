package seedu.duke.command;


import seedu.duke.CompanyList;

public class ListCompanyCommand extends Command{
    public ListCompanyCommand(String commandType){
        super(commandType);
    }

    @Override
    public void execute(CompanyList companyList) {
        System.out.println("list company command");
    }
}
