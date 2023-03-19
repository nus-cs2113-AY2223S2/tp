package seedu.duke.company;

import seedu.duke.exception.InputMismatchException;

import java.util.ArrayList;

public class CompanyList {

    private static ArrayList<Company> companyList;

    public CompanyList(ArrayList<Company> companyList) {
        this.companyList = companyList;
    }

    public boolean add(String companyName, int contactNumber, String contactEmail){
        try{
            Company newCompany = new Company(companyName, contactNumber, contactEmail);
            companyList.add(newCompany);
            return true;
        } catch(InputMismatchException e){
            return false;
        }
    }
}
