package seedu.duke.storage;

import seedu.duke.company.Company;
import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;

import java.io.FileWriter;
import java.io.IOException;

public class CompanyListEncoder extends Storage {


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
