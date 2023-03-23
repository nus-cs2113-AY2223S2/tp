package parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void extractSortBy_successful() {
        String sortBy = parser.extractSortBy("delete C");
        assertEquals("C", sortBy);
        sortBy = parser.extractSortBy("delete");
        assertEquals("", sortBy);

    }

}
