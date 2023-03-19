package seedu.duke.command;


import seedu.duke.company.CompanyList;

public class AddCommand extends Command {
    protected String companyName;
    protected String contactNumber;
    protected String contactEmail;

    public AddCommand(String commandType, String companyName, String contactNumber, String contactEmail) {
        super(commandType);
        this.companyName = companyName;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
    }

    @Override
    public void execute(CompanyList companyList) {
        companyList.add(companyName,Integer.parseInt(contactNumber),contactEmail);
    }
}
