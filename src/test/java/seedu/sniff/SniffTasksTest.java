package seedu.sniff;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void duplicateConsultationTest() {
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
            testList.addConsultation(animal, owner, date, time);
            assertEquals(1, testList.getNumberOfAppointments());
        } catch (SniffException e) {
            Assertions.fail("Should now throw error as inputs are valid");
        }
    }

    @Test
    void duplicateVaccinationTest() {
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
            testList.addVaccination(animal, owner, date, time, vaccine);
            assertEquals(2, testList.getNumberOfAppointments()); //1 vaccination + 1 consultation
        } catch (SniffException e) {
            Assertions.fail("Should now throw error as inputs are valid");
        }
    }

    @Test
    void duplicateSurgeryTest() {
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
            assertEquals(3, testList.getNumberOfAppointments()); //1 surgery + 1 vaccination + 1 consultation
        } catch (SniffException e) {
            Assertions.fail("Should now throw error as inputs are valid");
        }
    }
}
