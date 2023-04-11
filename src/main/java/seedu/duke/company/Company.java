package seedu.duke.company;

public class Company {

    public boolean isConfirmed;
    private String companyName;
    private int contactNumber;
    private String contactEmail;
    private String industry;

    public Company(String companyName, String industry, int contactNumber, String contactEmail){
        this.companyName = companyName;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.isConfirmed = false;
        this.industry = industry.toUpperCase();
    }

    public Company(String companyName, String industry, int contactNumber, String contactEmail, boolean isConfirmed){
        this.companyName = companyName;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.isConfirmed = isConfirmed;
        this.industry = industry.toUpperCase();
    }

    public String getCompanyName(){
        return companyName;
    }

    public String getIndustry(){
        return industry;
    }

    public int getContactNumber(){
        return contactNumber;
    }

    public String getContactEmail(){
        return contactEmail;
    }

    public String getConfirmStatus() {
        return (isConfirmed ? "Confirmed" : "Unconfirmed");
    }

    public void markConfirmed() {
        this.isConfirmed = true;
    }

    public void markUnconfirmed() {
        this.isConfirmed = false;
    }

    @Override
    public String toString() {
        return "Company name: " + companyName + "\nCompany contact number: " + contactNumber +
                "\nCompany contact email: "  + contactEmail + "\nCompany industry: " + industry +
                "\n[" + this.getConfirmStatus() + "]";
    }
}
