package seedu.duke.company;

import seedu.duke.exception.EmptyListException;
import seedu.duke.exception.InputMismatchException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class CompanyList {

    private static ArrayList<Company> companyList;

    public CompanyList(ArrayList<Company> companyList) {
        this.companyList = companyList;
    }
    public void add(String companyName, int contactNumber, String contactEmail) {
        Ui ui = new Ui();
        Company newCompany = new Company(companyName, contactNumber, contactEmail);
        companyList.add(newCompany);
        ui.showSuccessfulAdditionMessage(companyName);
    }

    public void printCompanyInformation() throws EmptyListException {
        if (companyList.isEmpty()) {
            throw new EmptyListException();
        }
        for (int i = 0; i < companyList.size(); i++) {
            System.out.println(i + 1);
            System.out.println(companyList.get(i));
        }
    }

    public void deleteCompanyInformation(int index) throws InvalidIndexException {
        if (index < 0 | index >= companyList.size()) {
            throw new InvalidIndexException();
        }
        Ui ui = new Ui();
        companyList.remove(index);
        ui.showSuccessfulDeletionMessage();
    }

    public void loadSampleCompanyInformation() throws InputMismatchException {
        Ui ui = new Ui();
        Company sampleCompany1 = new Company("Huawei", 80060114 , "APSupport@huawei.com");
        Company sampleCompany2 = new Company("Google", 91002500, "google@google.com");
        Company sampleCompany3 = new Company("Tiktok", 91231239, "tiktok@tiktok.com");
        companyList.add(sampleCompany1);
        companyList.add(sampleCompany2);
        companyList.add(sampleCompany3);
        ui.showSampleDataLoadedMessage();

    }

    public void purgeData(){
        companyList.clear();
    }
}
