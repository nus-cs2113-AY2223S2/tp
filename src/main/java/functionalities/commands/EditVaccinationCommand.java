package functionalities.commands;

import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;
import functionalities.SniffTasks;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Command to execute EditVaccination functionality
 */

public class EditVaccinationCommand extends Command{
    public String uid;
    private final Animal animal;
    private final Owner owner;
    private final String vaccine;
    private final LocalDate date;
    private final LocalTime time;

    public EditVaccinationCommand(String uid,String animalType, String animalName, String ownerName,
                              String contactNumber, String vaccine, LocalDate date,
                              LocalTime time) throws SniffException {
        this.uid = uid;
        this.animal = new Animal(animalType, animalName);
        this.owner = new Owner(ownerName, contactNumber);
        this.vaccine = vaccine;
        this.date = date;
        this.time = time;
    }
    /** Executes EditVaccination Command method located in SniffTasks class
     *
     * @param tasks The SniffTasks Class
     * @throws SniffException thrown when the input does not follow the format
     */
    @Override
    public void executeCommand(SniffTasks tasks) throws SniffException {
        tasks.editVaccination(uid, animal, owner, date, time, vaccine);
    }
}
