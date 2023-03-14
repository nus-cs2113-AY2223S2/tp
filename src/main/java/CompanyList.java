import eventus.company.Company;

import java.util.ArrayList;

public class CompanyList {

    ArrayList<Company> companies = new ArrayList<>();

    public boolean add(String companyName, int contactNumber, String contactEmail){
        try{
            Company newCompany = new Company(companyName, contactNumber, contactEmail);
            companies.add(newCompany);
            return true;
        } catch(seedu.duke.InputMismatchException e){
            return false;
        }
    }
}
