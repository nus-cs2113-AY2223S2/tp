package functionalities.commands;

import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;
import functionalities.SniffTasks;
import functionalities.appointments.Appointment;
import functionalities.appointments.Consultation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RemoveCommandTest {

    @Test
    void RemoveCommandNotNullTest() throws SniffException {
        String uid = "";
        RemoveCommand c = new RemoveCommand(uid);
        assertNotNull(c);
    }
}