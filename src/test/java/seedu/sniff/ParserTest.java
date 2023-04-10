package seedu.sniff;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import exception.SniffException;
import functionalities.commands.Command;
import functionalities.commands.ConsultationCommand;
import functionalities.commands.ExitCommand;
import functionalities.commands.FindCommand;
import functionalities.commands.ListCommand;
import functionalities.commands.RemoveCommand;
import functionalities.commands.SurgeryCommand;
import functionalities.commands.VaccinationCommand;
import functionalities.parser.Parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void consultationTest() {
        String consultationCommand = "consultation at/cat an/lulu on/jon cn/91919191 cd/2023-12-12 ct/19:00";
        Command consultationParsedCommand = null;
        try {
            consultationParsedCommand = Parser.parse(consultationCommand);
        } catch (SniffException e) {
            Assertions.fail("SniffException thrown when not expected");
        }
        assertTrue(consultationParsedCommand instanceof ConsultationCommand,
                "consultationParsedCommand is not an instance of ConsultationCommand");
    }

    @Test
    public void consultationTest_invalidTime_expectException() {
        String task = "consultation at/Dog an/Rover on/John cn/12345678 v/Rabies cd/2023-04-15 ct/10:30am";
        assertThrows(SniffException.class, () -> Parser.parse(task));
    }

    @Test
    public void consultationTest_invalidDate_expectException() {
        String task = "consultation at/Dog an/Rover on/John cn/12345678 cd/20-04-15 ct/10:30";
        assertThrows(SniffException.class, () -> Parser.parse(task));
    }

    @Test
    public void vaccinationTest() {
        String vaccinationCommand = "vaccination at/cat an/lulu on/jon cn/91919191 v/covid vd/2023-12-12 vt/19:00";
        Command vaccinationParsedCommand = null;
        try {
            vaccinationParsedCommand = Parser.parse(vaccinationCommand);
        } catch (SniffException e) {
            Assertions.fail("SniffException thrown when not expected");
        }
        assertTrue(vaccinationParsedCommand instanceof VaccinationCommand,
                "vaccinationParsedCommand is not an instance of VaccinationCommand");
    }

    @Test
    public void vaccinationTest_invalidContactNumber_expectException() {
        String task = "vaccination at/Dog an/Rover on/John cn/999 v/Rabies vd/20-04-15 vt/10:30";
        assertThrows(SniffException.class, () -> Parser.parse(task));
    }

    @Test
    public void vaccinationTest_invalidAnimalName_expectException() {
        String task = "vaccination at/123 an/Rover on/John cn/12345678 v/Rabies vd/20-04-15 vt/10:30";
        assertThrows(SniffException.class, () -> Parser.parse(task));
    }

    @Test
    public void surgeryTest() {
        String surgeryCommand = "surgery at/cat an/lulu on/jon cn/91919191 " +
                "sd/2023-12-12 st/19:00 ed/2023-12-12 et/20:00 p/H";
        Command surgeryParsedCommand = null;
        try {
            surgeryParsedCommand = Parser.parse(surgeryCommand);
        } catch (SniffException e) {
            Assertions.fail("SniffException thrown when not expected");
        }
        assertTrue(surgeryParsedCommand instanceof SurgeryCommand,
                "surgeryParsedCommand is not an instance of SurgeryCommand");
    }

    @Test
    public void surgeryTest_invalidDateTime_expectException() {
        String task = "surgery at/cat an/lulu on/jon cn/91919191 sd/2023-12-12 st/19:00 ed/2023-12-12 et/19:00 p/L";
        assertThrows(SniffException.class, () -> Parser.parse(task));
    }

    @Test
    public void surgeryTest_invalidDate_expectException() {
        String task = "surgery at/cat an/lulu on/jon cn/91919191 sd/20-12-12 st/19:00 ed/20-12-12 et/20:00 p/H";
        assertThrows(SniffException.class, () -> Parser.parse(task));
    }

    @Test
    public void findTest() {
        String findCommand = "find a/dog";
        String findCommand1 = "find t/surgery";
        String findCommand2 = "find uid/123";
        Command findParsedCommand = null;
        try {
            findParsedCommand = Parser.parse(findCommand);
            findParsedCommand = Parser.parse(findCommand1);
            findParsedCommand = Parser.parse(findCommand2);
        } catch (SniffException e) {
            Assertions.fail("SniffException thrown when not expected");
        }
        assertTrue(findParsedCommand instanceof FindCommand,
                "findParsedCommand is not an instance of FindCommand");
    }

    @Test
    public void listTest() {
        String listCommand = "list";
        Command listParsedCommand = null;
        try {
            listParsedCommand = Parser.parse(listCommand);
        } catch (SniffException e) {
            Assertions.fail("SniffException thrown when not expected");
        }
        assertTrue(listParsedCommand instanceof ListCommand,
                "listParsedCommand is not an instance of ListCommand");
    }

    @Test
    public void removeTest() {
        String removeCommand = "remove uid/C12345678A";
        Command removeParsedCommand = null;
        try {
            removeParsedCommand = Parser.parse(removeCommand);
        } catch (SniffException e) {
            Assertions.fail("SniffException thrown when not expected");
        }
        assertTrue(removeParsedCommand instanceof RemoveCommand,
                "removeParsedCommand is not an instance of RemoveCommand");
    }

    @Test
    public void byeTest() {
        String byeCommand = "bye";
        Command byeParsedCommand = null;
        try {
            byeParsedCommand = Parser.parse(byeCommand);
        } catch (SniffException e) {
            Assertions.fail("SniffException thrown when not expected");
        }
        assertTrue(byeParsedCommand instanceof ExitCommand,
                "byeParsedCommand is not an instance of ByeCommand");
    }
}
