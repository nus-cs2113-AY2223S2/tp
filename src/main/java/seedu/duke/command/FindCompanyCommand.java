package seedu.duke.command;

import seedu.duke.company.CompanyList;

public class FindCompanyCommand extends Command{

    protected String targetCompany;

    public FindCompanyCommand(String commandType, String targetCompany){
        super(commandType);
        this.targetCompany = targetCompany;
    }

    /**
     * Finds the companies by the company name within the company list
     *
     * @param companyList which contains a list of all the companies
     */
    @Override
    public void execute(CompanyList companyList){
        companyList.findCompany(targetCompany);
    }
}
