package seedu.duke.storage;

import seedu.duke.company.Company;
import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.NoSavedInformationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CompanyListDecoder extends Storage {

    private static final String filePath = "data/companyList.txt";

    public static void read(CompanyList companyList) {
        try {
            checkFileAccess(filePath);
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            if (!s.hasNext()) {
                throw new NoSavedInformationException();
            }
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parsedInput = parseInput(line);
                writeToCompanyList(companyList, parsedInput);
            }
        } catch (NoSavedInformationException e) {
            System.out.println("There is no saved data found in company list.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (InvalidIndexException e) {
            System.out.println("Invalid index. Please try again.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] parseInput(String line) {
        String[] parsedline = line.split("\\|");
        return parsedline;
    }

    private static void writeToCompanyList(CompanyList companyList, String[] parsedInput)
            throws InvalidIndexException, java.io.IOException{
        try {
            String companyName = parsedInput[0];
            int contactNumber = Integer.parseInt(parsedInput[1]);
            String contactEmail = parsedInput[2];
            int status = Integer.parseInt(parsedInput[3]);
            String industry = parsedInput[4];
            Company company = new Company(companyName, industry,
                    contactNumber, contactEmail, (status == 1 ? true : false ));
            ArrayList<Company> companyList1 = companyList.getCompanyList();
            companyList1.add(company);
        } catch (ArrayIndexOutOfBoundsException| NumberFormatException e) {
            System.out.println("Skipped line with error. To prevent this error, please do not edit the text file.");
        } finally {
            CompanyListEncoder.write(companyList);
        }
    }
}
