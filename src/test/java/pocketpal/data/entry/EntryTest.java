package pocketpal.data.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pocketpal.frontend.util.StringUtil;

import java.util.Arrays;

@DisplayName("Test Entry class")
public class EntryTest {
    private static final double AMOUNT = 2.3;
    private static final String DESCRIPTION = "Mango pudding";
    private static final Category CATEGORY = Category.FOOD;
    private Entry entry;

    @BeforeEach
    void init() {
        entry = new Entry(DESCRIPTION, AMOUNT, CATEGORY);
    }

    @Test
    void testGetters() {
        assertEquals(entry.getAmount(), AMOUNT);
        assertEquals(entry.getCategory(), CATEGORY);
        assertEquals(entry.getDescription(), DESCRIPTION);
    }

    @Test
    void testGetCategoryString() {
        Arrays.stream(Category.values()).forEach((categoryEnum) -> {
            final String expectedString = StringUtil.toTitleCase(String.valueOf(categoryEnum));
            entry.setCategory(categoryEnum);
            assertEquals(expectedString, entry.getCategoryString());
        });
    }

    @Test
    void testSetAmount() {
        final double newAmount = 43.12;
        entry.setAmount(newAmount);
        assertEquals(entry.getAmount(), newAmount);
    }

    @Test
    void testSetCategory() {
        final Category newCategory = Category.ENTERTAINMENT;
        entry.setCategory(newCategory);
        assertEquals(entry.getCategory(), newCategory);
    }

    @Test
    void testSetDescription() {
        final String newDescription = "Dried mango";
        entry.setDescription(newDescription);
        assertEquals(entry.getDescription(), newDescription);
    }
}
