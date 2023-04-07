package functionalities.commands;

import exception.SniffException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SurgeryCommandTest {

    @Test
    void surgeryCommandNotNull() throws SniffException {
        String animalName = "lulu";
        String animalType = "cat";
        String ownerName = "jon";
        String contactNumber = "91919191";
        LocalDate startDate = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        LocalDate endDate = LocalDate.now();
        LocalTime endTime = LocalTime.now();
        String priority = "L";
        SurgeryCommand c = new SurgeryCommand(animalType, animalName, ownerName,
                contactNumber, startDate, startTime, endDate, endTime, priority);
        assertNotNull(c);
    }
}
