package seedu.duke.command;

import seedu.duke.company.CompanyList;

public class LoadSampleCompanyCommand extends Command{

    public LoadSampleCompanyCommand(String commandType){
        super(commandType);
    }
    @Override
    public void execute(CompanyList companyList) {
        companyList.loadSampleCompanyInformation();
    }
}
