package seedu.duke;

import eventus.company.Company;

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
        } catch(seedu.duke.InputMismatchException e){
            return false;
        }
    }
    public void printCompanyInformation() throws EmptyListException {
        if (companyList.isEmpty()) {
            throw new EmptyListException();
        }
        for (int i = 0; i < companyList.size(); i++) {
            System.out.println(companyList.get(i));
        }
    }

    public void deleteCompanyInformation(int index) {
        Ui ui = new Ui();
        companyList.remove(index);
        ui.showSuccessfulDeletionMessage();
    }
}
