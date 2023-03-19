package seedu.duke.company;

import seedu.duke.exception.InputMismatchException;

public class Company {
    private String companyName;
    private int contactNumber;
    private String contactEmail;

    public Company(String companyName, int contactNumber, String contactEmail) throws InputMismatchException {
        this.companyName = companyName;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
    }

    public String getCompanyName(){
        return companyName;
    }

    public int getContactNumber(){
        return contactNumber;
    }

    public String getContactEmail(){
        return contactEmail;
    }
}
