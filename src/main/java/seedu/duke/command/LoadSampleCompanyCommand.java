package seedu.duke.command;

import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.storage.CompanyListEncoder;

import java.io.IOException;

public class LoadSampleCompanyCommand extends Command{

    public LoadSampleCompanyCommand(String commandType){
        super(commandType);
    }
    @Override
    public void execute(CompanyList companyList) {
        try {
            companyList.loadSampleCompanyInformation();
            CompanyListEncoder.write(companyList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidIndexException e) {
            throw new RuntimeException(e);
        }
    }
}
