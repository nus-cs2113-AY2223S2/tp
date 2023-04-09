package seedu.sniff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.DuplicateAppointmentException;
import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;
import functionalities.SniffTasks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class SniffTasksTest {

    private static final SniffTasks testList = new SniffTasks();
    @Test
    public void duplicateConsultationTest() {
        try {
            String animalName = "lulu";
            String animalType = "cat";
            String ownerName = "jon";
            String contactNumber = "91919191";
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            Animal animal = new Animal(animalType, animalName);
            Owner owner = new Owner(ownerName, contactNumber);
            testList.addConsultation(animal, owner, date, time);
            assertThrows(DuplicateAppointmentException.class,
                    () -> testList.checkConsultationDuplicate(animal, owner, date, time));
        } catch (SniffException e) {
            Assertions.fail("Should now throw error as inputs are valid");
        }
    }

    @Test
    public void duplicateVaccinationTest() {
        try {
            String animalName = "lulu";
            String animalType = "cat";
            String ownerName = "jon";
            String contactNumber = "91919191";
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            String vaccine = "LOLOLOLOL";
            Animal animal = new Animal(animalType, animalName);
            Owner owner = new Owner(ownerName, contactNumber);
            testList.addVaccination(animal, owner, date, time, vaccine);
            assertThrows(DuplicateAppointmentException.class,
                    () -> testList.checkVaccinationDuplicate(animal, owner, date, time, vaccine));
        } catch (SniffException e) {
            Assertions.fail("Should now throw error as inputs are valid");
        }
    }

    @Test
    public void duplicateSurgeryTest() {
        try {
            String animalName = "lulu";
            String animalType = "cat";
            String ownerName = "jon";
            String contactNumber = "91919191";
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = LocalDate.now().plusDays(2);
            LocalTime startTime = LocalTime.now();
            String priority = "H";
            LocalTime endTime = LocalTime.now().plusHours(2);
            Animal animal = new Animal(animalType, animalName);
            Owner owner = new Owner(ownerName, contactNumber);
            testList.addSurgery(animal, owner, priority, startDate, startTime, endDate, endTime);
            assertThrows(DuplicateAppointmentException.class,
                    () -> testList.checkSurgeryDuplicate(animal, owner, startDate, startTime, endDate, endTime));
        } catch (SniffException e) {
            Assertions.fail("Should now throw error as inputs are valid");
        }
    }
}
