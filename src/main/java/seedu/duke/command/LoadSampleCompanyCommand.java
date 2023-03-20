package seedu.duke.command;

import seedu.duke.company.CompanyList;
import seedu.duke.exception.InputMismatchException;

public class LoadSampleCompanyCommand extends Command{

    public LoadSampleCompanyCommand(String commandType){
        super(commandType);
    }
    @Override
    public void execute(CompanyList companyList) {
        try {
            companyList.loadSampleCompanyInformation();
        } catch (InputMismatchException e) {
            throw new RuntimeException(e);
        }
    }
}
