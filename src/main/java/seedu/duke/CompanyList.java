package seedu.duke;

import eventus.company.Company;

import java.util.ArrayList;

public class CompanyList {

    private static ArrayList<Company> companyList;

    public CompanyList(ArrayList<Company> companyList) {
        this.companyList = companyList;
    }
    Ui ui = new Ui();
    public boolean add(String companyName, int contactNumber, String contactEmail){
        try{
            Company newCompany = new Company(companyName, contactNumber, contactEmail);
            companyList.add(newCompany);
            ui.showSuccessfulAdditionMessage();

            return true;
        } catch(seedu.duke.InputMismatchException e){
            return false;
        }
    }

    public void deleteCompanyInformation(int index) {
        companyList.remove(index);
        ui.showSuccessfulDeletionMessage();
    }
}
