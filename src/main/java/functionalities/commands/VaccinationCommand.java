package functionalities.commands;

import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;
import functionalities.SniffTasks;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The VaccinationCommand class represents an add vaccination command to be executed.
 * This class is extends the abstract Command class and contains methods to executing the add vaccination command.
 */
public class VaccinationCommand extends Command {
    private final Animal animal;
    private final Owner owner;
    private final String vaccine;
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructs a new VaccinationCommand object with the specified parameters.
     *
     * @param animalType   the type of the animal for the vaccination appointment.
     * @param animalName    the name of the animal for the vaccination appointment.
     * @param ownerName     the name of the owner.
     * @param contactNumber the contact number of the owner.
     * @param vaccine       the type of vaccine used for the vaccination appointment.
     * @param date          the date of the vaccination appointment.
     * @param time          the time of the vaccination appointment.
     * @throws SniffException if any details are of the wrong format.
     */
    public VaccinationCommand(String animalType, String animalName, String ownerName,
                              String contactNumber, String vaccine, LocalDate date,
                              LocalTime time) throws SniffException {
        this.animal = new Animal(animalType, animalName);
        this.owner = new Owner(ownerName, contactNumber);
        this.vaccine = vaccine;
        this.date = date;
        this.time = time;
    }

    /**
     * Executes add vaccination method located in SniffTasks class
     *
     * @param tasks The SniffTasks Class
     * */
    @Override
    public void executeCommand(SniffTasks tasks) throws SniffException {
        tasks.addVaccination(animal, owner, date, time, vaccine);
    }
}
