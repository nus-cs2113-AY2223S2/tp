package functionalities.commands;

import exception.SniffException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SurgeryCommandTest {

    @Test
    void SurgeryCommandNotNull() {
        String animalName = "";
        String animalType = "";
        String ownerName = "";
        String contactNumber = "";
        String startDate = "";
        String startTime = "";
        String endDate = "";
        String endTime = "";
        String priority = "";
        SurgeryCommand c = new SurgeryCommand(animalType, animalName, ownerName,
                contactNumber, startDate, startTime, endDate, endTime, priority);
        assertNotNull(c);
    }
}