package seedu.duke.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class AddCommandTest {

    @Test
    void testSplitDetails_oneString_returnsStringSArrayInTwoParts() {
        String details = " -n Egg -e 21/03/2023";
        AddCommand addFood = new AddCommand(details);
        String[] expected = {"Egg", "21/03/2023"};
        assertArrayEquals(expected, addFood.splitDetails(details));
    }
}
