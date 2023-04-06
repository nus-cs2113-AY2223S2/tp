package seedu.duke.storage;

import seedu.duke.company.Company;
import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.NoSavedInformationException;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CompanyListDecoder extends Storage {

    private static final String filePath = "data/companyList.txt";

    public static void read(CompanyList companyList) {
        Ui ui = new Ui();
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
            ui.showLine();
            System.out.println("There is no saved data found in company list.");
            ui.showLine();
        } catch (FileNotFoundException e) {
            ui.showLine();
            System.out.println("File not found.");
            ui.showLine();
        } catch (InvalidIndexException e) {
            ui.showLine();
            System.out.println("Invalid index. Please try again.");
            ui.showLine();
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
        Ui ui = new Ui();
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
            ui.showLine();
            System.out.println("Skipped line with error. To prevent this error, please do not edit the text file.");
            ui.showLine();
        } finally {
            CompanyListEncoder.write(companyList);
        }
    }
}
