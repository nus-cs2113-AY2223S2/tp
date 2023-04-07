package functionalities.commands;

import exception.SniffException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConsultationCommandTest {

    @Test
    void consultationCommandNotNull() throws SniffException {
        String animalName = "lulu";
        String animalType = "cat";
        String ownerName = "jon";
        String contactNumber = "91919191";
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        ConsultationCommand c = new ConsultationCommand(animalType, animalName, ownerName,
                contactNumber, date, time);
        assertNotNull(c);
    }
}


