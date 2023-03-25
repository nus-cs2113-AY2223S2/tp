package seedu.duke.storage;

import seedu.duke.company.Company;
import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;

import java.io.FileWriter;
import java.io.IOException;

public class CompanyListEncoder extends Storage {

    private static int convertConfirmStatusToBinary(String confirmStatus) {
        if (confirmStatus.equals("Confirmed")) {
            return 1;
        }
        return 0;
    }
}
