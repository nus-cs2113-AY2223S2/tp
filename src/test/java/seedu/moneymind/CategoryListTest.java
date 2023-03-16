package seedu.moneymind;

import org.junit.jupiter.api.Test;
import seedu.moneymind.category.CategoryList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryListTest {

    /**
     * Setup the list with two categories.
     */
    public void setup() {
        CategoryList.addCategory("food");
        CategoryList.addCategory("book");
    }

    /**
     * Test if the category is added to the list.
     * Expected outcome: Category is added to the list.
     */
    @Test
    void getCategory_index_exptOutcome() {
        setup();
        CategoryList.addCategory("test");    //to ensure that the list is not empty
        assertEquals(CategoryList.categories.get(0), CategoryList.getCategory(0));
        clear();
    }

    void clear() {
        CategoryList.categories.clear();
    }
}
