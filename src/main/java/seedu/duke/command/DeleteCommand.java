package seedu.duke.command;


import seedu.duke.company.CompanyList;
import seedu.duke.exception.EmptyListException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.storage.CompanyListEncoder;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command{
    protected int taskNum;
    Ui ui = new Ui();
    public DeleteCommand(String commandType, int taskNum){
        super(commandType);
        this.taskNum = taskNum;
    }

    /**
     * Deletes a company from the list of companies
     *
     * @param companyList which contains a list of all the companies
     * @throws InvalidIndexException if error occurred due to invalid index
     * @throws IOException if error occurred during file writing
     */
    @Override
    public void execute(CompanyList companyList) {
        try {
            companyList.deleteCompanyInformation(taskNum);
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
