package seedu.moneymind.commands;

import org.junit.jupiter.api.Test;
import seedu.moneymind.category.CategoryList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryCommandTest extends CommandTest {

    public CategoryCommandTest() {
        super();
    }

    @Test
    void addCategory_oneCategoryAndNoBudget_expectThreeCategoryInCategoryList() {
        setup();
        executeInput("category travel");
        assertEquals(3, CategoryList.categories.size());
        assertEquals("travel", CategoryList.categories.get(2).getName());
        assertEquals(0, CategoryList.categories.get(2).getBudget());
        clear();
    }

    @Test
    void addCategory_sameCategory_expectCategoryExistsMessage() {
        setup();
        String terminalOutput = executeInput("category food").toString();
        assertEquals("Category already exists" + System.lineSeparator(), terminalOutput);
        assertEquals(2, CategoryList.categories.size());
        assertEquals("food", CategoryList.categories.get(0).getName());
        clear();
    }

    @Test
    void addCategory_dummyBudget_expectGivingPositiveIntegerMessage() {
        setup();
        String terminalOutput = executeInput("category travel b/ad").toString();
        assertEquals("Please give a non-negative integer for budget" + System.lineSeparator(), terminalOutput);
        assertEquals(2, CategoryList.categories.size());
        assertEquals("food", CategoryList.categories.get(0).getName());
        clear();
    }

    @Test
    void addCategory_negativeBudget_expectGivingPositiveIntegerMessage() {
        setup();
        String terminalOutput = executeInput("category travel b/-12").toString();
        assertEquals("Please give a non-negative integer for budget" + System.lineSeparator(), terminalOutput);
        assertEquals(2, CategoryList.categories.size());
        clear();
    }

    @Test
    void addCategory_oneCategoryWithBudget_expectThreeCategoryInCategoryList() {
        setup();
        executeInput("category travel b/100");
        assertEquals(3, CategoryList.categories.size());
        assertEquals("travel", CategoryList.categories.get(2).getName());
        assertEquals(100, CategoryList.categories.get(2).getBudget());
        clear();
    }

    @Test
    void addCategory_emptyDescription_expectEmptyDescriptionMessage() {
        setup();
        String terminalOutput = executeInput("category").toString();
        assertEquals("OOPS!!! The description of a category cannot be empty."
                + System.lineSeparator(), terminalOutput);
        assertEquals(2, CategoryList.categories.size());
        clear();
    }

    @Test
    void addCategory_emptyCategoryName_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("category b/100").toString();
        assertEquals("Please following the correct format: category <name> [(optional) b/<budget number>]\n" +
                "Remember do not leave any things inside the brackets empty!"
                + System.lineSeparator(), terminalOutput);
        assertEquals(2, CategoryList.categories.size());
        clear();
    }

    @Test
    void addCategory_emptyBudget_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("category travel b/").toString();
        assertEquals("Please following the correct format: category <name> [(optional) b/<budget number>]\n" +
                "Remember do not leave any things inside the brackets empty!"
                + System.lineSeparator(), terminalOutput);
        assertEquals(2, CategoryList.categories.size());
        clear();
    }

    @Test
    void addCategory_spareSlashSpecifier_expectCorrectFormatMessage() {
        setup();
        String terminalOutput = executeInput("category fsd / b/123").toString();
        assertEquals("Please following the correct format: category <name> [(optional) b/<budget number>]\n" +
                "Remember do not leave any things inside the brackets empty!"
                + System.lineSeparator(), terminalOutput);
        assertEquals(2, CategoryList.categories.size());
        clear();
    }

}
