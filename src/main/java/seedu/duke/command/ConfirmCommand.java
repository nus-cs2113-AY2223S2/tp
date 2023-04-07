package seedu.duke.command;

import seedu.duke.exception.EmptyListException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.company.CompanyList;
import seedu.duke.storage.CompanyListEncoder;
import seedu.duke.ui.Ui;

import java.io.IOException;


public class ConfirmCommand extends Command {
    protected int companyNum;

    Ui ui =  new Ui();

    public ConfirmCommand(String commandType, int companyNum){
        super(commandType);
        this.companyNum = companyNum;
    }

    /**
     * Confirms a company's attendance to the event
     *
     * @param companyList which contains a list of all the companies
     * @throws InvalidIndexException if error occurred due to invalid index
     * @throws IOException if error occurred during file writing
     * @throws EmptyListException if error occurred due to empty list
     */
    @Override
    public void execute(CompanyList companyList){
        try {
            companyList.markConfirm(companyNum);
            CompanyListEncoder.write(companyList);
        } catch (InvalidIndexException | IOException err) {
            ui.showLine();
            System.out.println("Invalid index provided! Please try again");
            ui.showLine();
        } catch (EmptyListException e) {
            ui.showLine();
            System.out.println("Nothing inside company list");
            ui.showLine();
        }
    }


}
