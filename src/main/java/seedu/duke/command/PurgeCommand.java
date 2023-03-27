package seedu.duke.command;

import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.storage.CompanyListEncoder;

import java.io.IOException;

public class PurgeCommand extends Command{

    public PurgeCommand(String commandType) {
        super(commandType);
    }

    /**
     * Deletes a company list information
     *
     * @param companyList which contains a list of all the companies
     * @throws InvalidIndexException if error occurred due to invalid index
     * @throws IOException if error occurred during file writing
     */
    @Override
    public void execute(CompanyList companyList) {
        try {
            companyList.purgeData();
            CompanyListEncoder.write(companyList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidIndexException e) {
            throw new RuntimeException(e);
        }
    }
}
