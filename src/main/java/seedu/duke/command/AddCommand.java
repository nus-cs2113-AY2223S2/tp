package seedu.duke.command;

import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.storage.CompanyListEncoder;

import java.io.IOException;

public class AddCommand extends Command {
    protected String companyName;
    protected int contactNumber;
    protected String contactEmail;
    protected String industry;

    public AddCommand(String commandType, String industry, String companyName, int contactNumber, String contactEmail) {
        super(commandType);
        this.companyName = companyName;
        this.industry = industry;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
    }

    @Override
    public void execute(CompanyList companyList) {
        try {
            companyList.add(companyName, industry, contactNumber, contactEmail);
            CompanyListEncoder.write(companyList);
        } catch (InvalidIndexException | IOException err) {
            System.out.println("Unsuccessful in saving your file :/");
        }
    }
}
