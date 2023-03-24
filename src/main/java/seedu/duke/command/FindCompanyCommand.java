package seedu.duke.command;

import seedu.duke.company.CompanyList;

public class FindCompanyCommand extends Command{

    protected String targetCompany;

    public FindCompanyCommand(String commandType, String targetCompany){
        super(commandType);
        this.targetCompany = targetCompany;
    }

    @Override
    public void execute(CompanyList companyList){
        companyList.findCompany(targetCompany);
    }
}
