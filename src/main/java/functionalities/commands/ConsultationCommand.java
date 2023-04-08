package functionalities.commands;

import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;
import functionalities.SniffTasks;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The ConsultationCommand class represents an add consultation command to be executed.
 * This class is extends the abstract Command class and contains methods to executing the add consultation command.
 */
public class ConsultationCommand extends Command {

    private final Animal animal;
    private final Owner owner;
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructs a new ConsultationCommand object with the specified parameters.
     *
     * @param animalType    the type of the animal for the consultation appointment.
     * @param animalName    the name of the animal for the consultation appointment.
     * @param ownerName     the name of the owner.
     * @param contactNumber the contact number of the owner.
     * @param date          the date of the consultation appointment.
     * @param time          the time of the consultation appointment.
     * @throws SniffException if any details are of the wrong format.
     */
    public ConsultationCommand(String animalType, String animalName,
                               String ownerName, String contactNumber, LocalDate date,
                               LocalTime time) throws SniffException {
        this.animal = new Animal(animalType, animalName);
        this.owner = new Owner(ownerName, contactNumber);
        this.date = date;
        this.time = time;
    }

    /**
     * Executes add consultation method located in SniffTasks class
     *
     * @param tasks The SniffTasks Class
     */
    @Override
    public void executeCommand(SniffTasks tasks) throws SniffException {
        tasks.addConsultation(animal, owner, date, time);
    }
}
