package seedu.duke.storage;

import seedu.duke.company.Company;
import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;

import java.io.FileWriter;
import java.io.IOException;

public class CompanyListEncoder extends Storage {

    public static final String filePath = "data/companyList.txt";

    public static void write(CompanyList companyList) throws IOException, InvalidIndexException {
        try {
            FileWriter fw = new FileWriter(filePath);
            int numberOfCompanies = companyList.getNumberOfCompanies();
            // If the last company in the array list is deleted clear the entire text file
            // Since the for loop only iterates if there is more than one company in companyList
            if (numberOfCompanies == 0) {
                fw.write("");
                fw.close();
            }
            // Iterate through company in companyList and parse company information for storage
            for (int i = 0; i < numberOfCompanies; i += 1) {
                Company company = companyList.getCompany(i);
                parseAndWriteToFile(company, fw);
            }
            fw.close();
        } catch (IOException | InvalidIndexException e) {
            System.out.println("File not found");
        }
    }

    private static void parseAndWriteToFile(Company company, FileWriter fw) throws IOException {
        String companyName = company.getCompanyName();
        int contactNumber = company.getContactNumber();
        String contactEmail = company.getContactEmail();
        int confirmStatus = convertConfirmStatusToBinary(company.getConfirmStatus());
        String industry = company.getIndustry();
        fw.write(companyName + "|" + contactNumber + "|" + contactEmail + "|" + confirmStatus + "|" +
                industry + System.lineSeparator());
    }

    private static int convertConfirmStatusToBinary(String confirmStatus) {
        if (confirmStatus.equals("Confirmed")) {
            return 1;
        }
        return 0;
    }
}
