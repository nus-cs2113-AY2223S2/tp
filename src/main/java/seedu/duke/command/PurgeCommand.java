package seedu.duke.command;

import seedu.duke.company.CompanyList;

public class PurgeCommand extends Command{

    public PurgeCommand(String commandType) {
        super(commandType);
    }

    @Override
    public void execute(CompanyList companyList) {
        companyList.purgeSampleData();
    }
}
