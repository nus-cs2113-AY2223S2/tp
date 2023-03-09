package parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void extractCommandKeyword_successful() {
        String command = Parser.extractCommandKeyword("add amt/2 t/2359 cat/food");
        assertEquals("add" , command);
    }

    @Test
    void extractIndex_successful() {
        int index = Parser.extractIndex("delete 3");
        assertEquals(3, index);
    }
}