package seedu.duke.command;

import seedu.duke.company.CompanyList;

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
        this.industry = industry;
    }

    @Override
    public void execute(CompanyList companyList) {
        companyList.add(companyName, industry, contactNumber, contactEmail);
    }
}
