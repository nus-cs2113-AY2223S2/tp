package seedu.sniff;

import static functionalities.parser.Parser.parseConsultationCommand;
import static functionalities.parser.Parser.parseSurgeryCommand;
import static functionalities.parser.Parser.parseVaccinationCommand;

//import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import exception.SniffException;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void consultationTest() throws SniffException {
        String task = "consultation at/cat an/lulu on/jon cn/91919191 cd/2023-12-12 ct/19:00";
        parseConsultationCommand(task);
        assertTrue(true);
    }

    @Test
    public void vaccinationTest() throws SniffException {
        String task = "vaccination at/cat an/lulu on/jon cn/91919191 v/covid vd/2023-12-12 vt/19:00";
        parseVaccinationCommand(task);
        assertTrue(true);
    }

    @Test
    public void surgeryTest() throws SniffException {
        String task = "surgery at/cat an/lulu on/jon cn/91919191 sd/2023-12-12 st/19:00 ed/2023-12-12 et/20:00 p/H";
        parseSurgeryCommand(task);
        assertTrue(true);
    }

    @Test
    public void dummyFindTest() {
        assertTrue(true);
    }

    @Test
    public void dummyListTest() {
        assertTrue(true);
    }

    @Test
    public void dummyByeTest() {
        assertTrue(true);
    }
}
