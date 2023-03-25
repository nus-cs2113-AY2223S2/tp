package seedu.duke.storage;

import seedu.duke.company.Company;
import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.NoSavedInformationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CompanyListDecoder extends Storage {

    public static final String filePath = "data/companyList.txt";

    private static String[] parseInput(String line) {
        String[] parsedline = line.split("\\|");
        return parsedline;
    }

    private static void writeToCompanyList(CompanyList companyList, String[] parsedInput) throws InvalidIndexException {
        String companyName = parsedInput[0];
        int contactNumber = Integer.parseInt(parsedInput[1]);
        String contactEmail = parsedInput[2];
        int status = Integer.parseInt(parsedInput[3]);
        String industry = parsedInput[4];
        companyList.add(companyName, industry, contactNumber, contactEmail);
        int currCompany = companyList.getNumberOfCompanies() - 1;
        // If status was previously indicated as confirmed, mark the status of the company as confirmed in companyList
        if (status == 1) {
            Company company = companyList.getCompany(currCompany);
            company.markConfirmed();
        }
}
