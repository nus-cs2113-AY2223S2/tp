package seedu.duke.company;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyListException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class CompanyListTest {

    @Test
    void getNumberOfCompanies_zeroCompanies_success() {
        assertEquals(0, new CompanyList(new ArrayList<>(), new Ui()).getNumberOfCompanies());
    }

    @Test
    void getNumberOfCompanies_oneCompany_success() {
        CompanyList companyList = companyListWithOneCompany();
        assertEquals(1, companyList.getNumberOfCompanies());
    }

    @Test
    void deleteCompanyInformation_negativeIndex_exceptionThrown() {
        CompanyList companyList = companyListWithOneCompany();
        try {
            companyList.deleteCompanyInformation(-1);
            fail();
        } catch (InvalidIndexException e) {
            assertNull(e.getMessage());
        } catch (EmptyListException e) {
            fail();
        }
    }

    @Test
    void deleteCompanyInformation_emptyList_exceptionThrown() {
        CompanyList companyList = new CompanyList(new ArrayList<>(), new Ui());
        try {
            companyList.deleteCompanyInformation(1);
            fail();
        } catch (InvalidIndexException e) {
            fail();
        } catch (EmptyListException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    void deleteCompanyInformation_invalidIndex_exceptionThrown() {
        CompanyList companyList = companyListWithOneCompany();
        try {
            companyList.deleteCompanyInformation(2);
            fail();
        } catch (InvalidIndexException e) {
            assertNull(e.getMessage());
        } catch (EmptyListException e) {
            fail();
        }
    }

    @Test
    void deleteCompanyInformation_correctIndex_success() {
        CompanyList companyList = companyListWithOneCompany();
        try {
            companyList.deleteCompanyInformation(0);
        } catch (InvalidIndexException | EmptyListException e) {
            fail();
        }
    }

    @Test
    void purgeData_oneCompany_success() {
        CompanyList companyList = companyListWithOneCompany();
        try {
            companyList.purgeData();
        } catch (EmptyListException e) {
            fail();
        }
    }

    @Test
    void purgeData_emptyList_exceptionThrown() {
        CompanyList companyList = new CompanyList(new ArrayList<>(), new Ui());
        try {
            companyList.purgeData();
            fail();
        } catch (EmptyListException e) {
            assertNull(e.getMessage());
        }
    }

    // Abstract method to create company list with one company for reuse
    public static CompanyList companyListWithOneCompany() {
        Company company = new Company("NUS", "University", 91001234,
                "hello@nus.com");
        ArrayList<Company> companies = new ArrayList<>();
        companies.add(company);
        return new CompanyList(companies, new Ui());
    }
}