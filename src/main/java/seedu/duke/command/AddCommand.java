package seedu.duke.command;


import seedu.duke.company.CompanyList;

public class AddCommand extends Command {
    protected String companyName;
    protected int contactNumber;
    protected String contactEmail;

    public AddCommand(String commandType, String companyName, int contactNumber, String contactEmail) {
        super(commandType);
        this.companyName = companyName;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
    }

    @Override
    public void execute(CompanyList companyList) {
        companyList.add(companyName,contactNumber,contactEmail);
    }
}
