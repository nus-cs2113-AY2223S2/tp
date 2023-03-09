public class Company {
    private String companyName;
    private String contactNumber;
    private String contactEmail;

    public Company(String companyName, String contactNumber, String contactEmail){
        this.companyName = companyName;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
    }

    public String getCompanyName(){
        return companyName;
    }

    public String getContactNumber(){
        return contactNumber;
    }

    public String getContactEmail(){
        return contactEmail;
    }
}
