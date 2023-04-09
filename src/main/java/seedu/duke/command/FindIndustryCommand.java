package seedu.duke.command;

import seedu.duke.company.CompanyList;

public class FindIndustryCommand extends Command{

    protected String targetIndustry;

    public FindIndustryCommand(String commandType, String targetIndustry) {
        super(commandType);
        this.targetIndustry = targetIndustry;
    }

    /**
     * Finds the companies by the industry within the company list
     *
     * @param companyList which contains a list of all the companies
     */
    @Override
    public void execute(CompanyList companyList) {
        companyList.findIndustry(targetIndustry);
    }
}
