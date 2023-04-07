package functionalities.commands;

import exception.SniffException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class VaccinationCommandTest {

    @Test
    void vaccinationCommandNotNull() throws SniffException {
        String animalName = "lulu";
        String animalType = "cat";
        String ownerName = "jon";
        String contactNumber = "91919191";
        String vaccine = "covid";
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        VaccinationCommand c = new VaccinationCommand(animalType, animalName, ownerName,
                contactNumber, vaccine, date, time);
        assertNotNull(c);
    }
}
