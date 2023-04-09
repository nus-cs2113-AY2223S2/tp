package functionalities.parser;

import exception.SniffException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArchiveParserTest {

    @Test
    public void testAddConsult() {
        String content = "C21066208V | 2023-12-12 | 19:00 | lulu | cat | jon | 91919191";
        try {
            ArchiveParser.addConsult(content);
        } catch (SniffException e) {
            Assertions.fail("SniffException thrown when not expected");
        }
    }

    @Test
    public void testAddVaccine() {
        String content = "V44204401D | covid | 2023-12-12 | 19:00 | lulu | cat | jon | 91919191";
        try {
            ArchiveParser.addVaccine(content);
        } catch (SniffException e) {
            Assertions.fail("SniffException thrown when not expected");
        }
    }

    @Test
    public void testAddSurgery() {
        String content = "S61412448R | H | lulu | cat | jon | 91919191 | 2023-12-12 | 19:00 | 2023-12-12 | 20:00";
        try {
            ArchiveParser.addSurgery(content);
        } catch (SniffException e) {
            Assertions.fail("SniffException thrown when not expected");
        }
    }
}
