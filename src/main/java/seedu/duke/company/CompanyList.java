package seedu.duke.company;

import seedu.duke.exception.EmptyListException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class CompanyList {

    private static ArrayList<Company> companyList;
    private static Ui ui;

    public CompanyList(ArrayList<Company> companyList, Ui ui) {
        this.companyList = companyList;
        this.ui = ui;
    }

    public static ArrayList<Company> getCompanyList() {
        return companyList;
    }
    public static void add(String companyName, String industry, int contactNumber, String contactEmail) {
        companyName = companyName.strip().toUpperCase();
        contactEmail = contactEmail.strip().toUpperCase();
        Company newCompany = new Company(companyName, industry, contactNumber, contactEmail);
        if (!isDuplicateCompany(companyName)) {
            companyList.add(newCompany);
            ui.showSuccessfulAdditionMessage(companyName);
        } else {
            ui.showLine();
            System.out.println("Company already exists in the list!");
            ui.showLine();
        }
    }

    public static boolean isDuplicateCompany(String companyName){
        for (int i = 0; i < companyList.size(); i++) {
            String companyAlreadyAdded = companyList.get(i).getCompanyName();
            if (companyAlreadyAdded.contains(companyName)) {
                return true;
            }
        }
        return false;
    }

    public void printCompanyInformation() throws EmptyListException {
        if (companyList.isEmpty()) {
            throw new EmptyListException();
        }
        ui.showLine();
        for (int i = 0; i < companyList.size(); i++) {
            System.out.println(i + 1);
            System.out.println(companyList.get(i));
        }
        ui.showLine();
    }

    public int getNumberOfCompanies() {
        return companyList.size();
    }

    public void deleteCompanyInformation(int index) throws InvalidIndexException, EmptyListException {
        if (companyList.isEmpty()){
            throw new EmptyListException();
        }
        if (index < 0 | index >= companyList.size()) {
            throw new InvalidIndexException();
        }
        companyList.remove(index);
        ui.showSuccessfulDeletionMessage();
    }

    public Company getCompany(int index) throws InvalidIndexException {
        if (index < 0 | index >= companyList.size()) {
            throw new InvalidIndexException();
        }
        return companyList.get(index);
    }

    public void printUnconfirmed() throws EmptyListException {
        if (companyList.isEmpty()) {
            throw new EmptyListException();
        }
        ui.showLine();
        for (int i = 0; i < companyList.size(); i += 1){
            Company company = companyList.get(i);
            if (!company.isConfirmed){
                System.out.println((i + 1));
                System.out.println(companyList.get(i));
            }
        }
        ui.showLine();
    }

    public void findIndustry(String targetIndustry){
        ArrayList<Company> sortedCompanyList = new ArrayList<>();
        for (Company company : companyList){
            if(company.getIndustry().equals(targetIndustry)){
                sortedCompanyList.add(company);
            }
        }
        ui.showSortedCompanyList(targetIndustry, sortedCompanyList);
    }

    public void findCompany(String targetCompany){
        ArrayList<Company> sortedCompanyList = new ArrayList<>();
        targetCompany = targetCompany.toLowerCase();
        for (Company company : companyList){
            int idx = companyList.indexOf(company) + 1;
            if (company.getCompanyName().toLowerCase().contains(targetCompany)){
                ui.showCompanyFoundMessage(company, idx);
                sortedCompanyList.add(company);
            }
        }
        if (sortedCompanyList.isEmpty()){
            ui.showCompanyNotFoundMessage(targetCompany);
        }
    }

    public void loadSampleCompanyInformation() {
        add("Huawei", "Tech", 80060114 , "APSupport@huawei.com");
        add("Google", "Tech", 91002500, "google@google.com");
        add("Tiktok", "Social Media", 91231239, "tiktok@tiktok.com");
        ui.showSampleDataLoadedMessage();
    }

    public void purgeData() throws EmptyListException {
        if (companyList.isEmpty()) {
            throw new EmptyListException();
        }
        companyList.clear();
        ui.showSuccessfulPurgingMessage();
    }

    public void markConfirm(int companyNum) throws InvalidIndexException, EmptyListException {
        if (companyList.isEmpty()) {
            throw new EmptyListException();
        }
        if (companyNum < 0 | companyNum >= companyList.size()) {
            throw new InvalidIndexException();
        }
        Company company = companyList.get(companyNum);
        company.markConfirmed();
        ui.showSuccessfulConfirmedMessage();
    }

    public void markUnconfirm(int companyNum) throws InvalidIndexException, EmptyListException {
        if (companyList.isEmpty()) {
            throw new EmptyListException();
        }
        if (companyNum < 0 | companyNum >= companyList.size()) {
            throw new InvalidIndexException();
        }
        Company company = companyList.get(companyNum);
        company.markUnconfirmed();
        ui.showSuccessfulConfirmedMessage();
    }
}
