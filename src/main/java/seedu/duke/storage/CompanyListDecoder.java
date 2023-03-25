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

}
