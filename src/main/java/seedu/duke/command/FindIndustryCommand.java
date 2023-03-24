package seedu.duke.command;

import seedu.duke.company.CompanyList;

public class FindIndustryCommand extends Command{

    protected String industry;

    public FindIndustryCommand(String commandType, String industry){
        super(commandType);
        this.industry = industry;
    }

    @Override
    public void execute(CompanyList companyList){
    }
}
