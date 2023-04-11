package seedu.duke.command;

import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.storage.CompanyListEncoder;

import java.io.IOException;

public class LoadSampleCompanyCommand extends Command{

    public LoadSampleCompanyCommand(String commandType){
        super(commandType);
    }

    /**
     * Loads a sample company list for user testing
     *
     * @param companyList which contains a list of all the companies
     * @throws InvalidIndexException if error occurred due to invalid index
     * @throws IOException if error occurred during file writing     */
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
