package seedu.duke.company;

public class Company {

    public boolean isConfirmed;
    private String companyName;
    private int contactNumber;
    private String contactEmail;



    public Company(String companyName, int contactNumber, String contactEmail){
        this.companyName = companyName;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.isConfirmed = false;
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

    public String getConfirmStatus() { return (isConfirmed ? "Confirmed" : "Unconfirmed"); }

    public void markConfirmed() {
        this.isConfirmed = true;
    }

    public void markUnconfirmed() {
        this.isConfirmed = false;
    }

    @Override
    public String toString() {
        return "Company name: " + companyName + "\nCompany contact number: " + contactNumber +
                "\nCompany contact email: "  + contactEmail + "\n[" + this.getConfirmStatus()
                 + "]";
    }
}
