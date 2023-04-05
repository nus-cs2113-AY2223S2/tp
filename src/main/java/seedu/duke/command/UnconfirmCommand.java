package seedu.duke.command;

import seedu.duke.company.CompanyList;
import seedu.duke.exception.EmptyListException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.storage.CompanyListEncoder;

import java.io.IOException;

public class UnconfirmCommand extends Command {
    protected int companyNum;

    public UnconfirmCommand(String commandType, int companyNum){
        super(commandType);
        this.companyNum = companyNum;
    }

    /**
     * Unconfirms a company's attendance to the event
     *
     * @param companyList which contains a list of all the companies
     * @throws InvalidIndexException if error occurred due to invalid index
     * @throws IOException if error occurred during file writing
     * @throws EmptyListException if error occurred due to empty list
     */
    @Override
    public void execute(CompanyList companyList){
        try {
            companyList.markUnconfirm(companyNum);
            CompanyListEncoder.write(companyList);
        } catch (InvalidIndexException | IOException e) {
            System.out.println("Invalid index provided! Please try again");
        } catch (EmptyListException e) {
            System.out.println("Nothing inside company list");
        }
    }

}
