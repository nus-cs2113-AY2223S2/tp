package parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    Parser parser = new Parser();
    @Test
    void extractCommandKeyword_successful() {
        String command = parser.extractCommandKeyword("add amt/2 t/2359 cat/food");
        assertEquals("add" , command);
    }

    @Test
    void extractIndex_successful() {
        int index = parser.extractIndex("delete 3");
        assertEquals(3, index);
    }

    @Test
    void extractIndex_unsuccessful() {
        int index = parser.extractIndex("testing string");
        assertEquals(0, index);
    }

    @Test
    void extractSortBy_successful() {
        String sortBy = parser.extractSortBy("delete C");
        assertEquals("C", sortBy);
        sortBy = parser.extractSortBy("delete");
        assertEquals("", sortBy);
    }

    @Test
    void isMonthlyOverview_true() {
        boolean isMonthlyOverview = parser.isMonthlyOverview("overview March 2021");
        assertTrue(isMonthlyOverview);
    }

    @Test
    void isMonthlyOverview_false() {
        boolean isMonthlyOverview = parser.isMonthlyOverview("overview 2021");
        assertFalse(isMonthlyOverview);
    }

    @Test
    void extractMonth_successful() {
        String validMonth = parser.extractMonth("overview March 2021");
        String invalidMonth = parser.extractMonth("overview 2021");
        assertEquals("march", validMonth);
        assertNull(invalidMonth);
    }

    @Test
    void extractYear_successful() {
        String monthlyOverviewYear = parser.extractYear("overview March 2021");
        String yearlyOverviewYear = parser.extractYear("overview 2022");
        String invalidYear = parser.extractYear("overview");
        assertEquals("2021", monthlyOverviewYear);
        assertEquals("2022", yearlyOverviewYear);
        assertEquals("", invalidYear);
    }

}
