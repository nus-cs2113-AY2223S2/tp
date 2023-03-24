package seedu.duke.command;

import seedu.duke.company.CompanyList;

public class FindIndustryCommand extends Command{

    protected String targetIndustry;

    public FindIndustryCommand(String commandType, String targetIndustry){
        super(commandType);
        this.targetIndustry = targetIndustry;
    }

    @Override
    public void execute(CompanyList companyList){
        companyList.findIndustry(targetIndustry);
    }
}
