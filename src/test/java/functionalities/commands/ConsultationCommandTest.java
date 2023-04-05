package functionalities.commands;

import exception.SniffException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConsultationCommandTest {

    @Test
    void consultationCommandNotNull() throws SniffException {
        String animalName = "";
        String animalType = "";
        String ownerName = "";
        String contactNumber = "";
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        ConsulationCommand c = new ConsulationCommand(animalType, animalName, ownerName,
                contactNumber, date, time);
        assertNotNull(c);
    }
}


