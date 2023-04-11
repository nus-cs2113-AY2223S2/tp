package seedu.duke.command;

import seedu.duke.company.CompanyList;
import seedu.duke.exception.EmptyListException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.storage.CompanyListEncoder;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class PurgeCommand extends Command{

    Ui ui = new Ui();

    public PurgeCommand(String commandType) {
        super(commandType);
    }

    /**
     * Deletes a company list information
     *
     * @param companyList which contains a list of all the companies
     * @throws InvalidIndexException if error occurred due to invalid index
     * @throws IOException if error occurred during file writing
     * @throws EmptyListException if error occurred due to empty list
     */
    @Override
    public void execute(CompanyList companyList) {
        try {
            companyList.purgeData();
            CompanyListEncoder.write(companyList);
        } catch (EmptyListException err){
            ui.showLine();
            System.out.println("Nothing inside company list");
            ui.showLine();
        } catch (InvalidIndexException | IOException e) {
            ui.showLine();
            System.out.println("Unsuccessful in saving your file :/");
            ui.showLine();
        }
    }
}
