package seedu.duke.command;

import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.storage.CompanyListEncoder;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class AddCommand extends Command {
    protected String companyName;
    protected int contactNumber;
    protected String contactEmail;
    protected String industry;

    Ui ui = new Ui();

    public AddCommand(String commandType, String industry, String companyName, int contactNumber, String contactEmail) {
        super(commandType);
        this.companyName = companyName;
        this.industry = industry;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
    }

    /**
     * Adds a new company into the company list
     *
     * @param companyList which contains a list of all the companies
     * @throws InvalidIndexException if error occurred due to invalid index
     * @throws IOException if error occurred during file writing
     */
    @Override
    public void execute(CompanyList companyList) {
        try {
            companyList.add(companyName, industry, contactNumber, contactEmail);
            CompanyListEncoder.write(companyList);
        } catch (InvalidIndexException | IOException err) {
            ui.showLine();
            System.out.println("Unsuccessful in saving your file :/");
            ui.showLine();
        }
    }
}
