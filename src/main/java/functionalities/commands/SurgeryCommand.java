package functionalities.commands;

import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;
import functionalities.SniffTasks;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The SurgeryCommand class represents an add surgery command to be executed.
 * This class is extends the abstract Command class and contains methods to executing the add surgery command.
 */
public class SurgeryCommand extends Command {

    private final Animal animal;
    private final Owner owner;
    private final String priority;
    private final LocalDate startDate;
    private final LocalTime startTime;
    private final LocalDate endDate;
    private final LocalTime endTime;

    /**
     * Constructs a new SurgeryCommand object with the specified parameters.
     *
     * @param animalType    the type of the animal for the surgery appointment.
     * @param animalName    the name of the animal for the surgery appointment.
     * @param ownerName     the name of the owner.
     * @param contactNumber the contact number of the owner.
     * @param startDate     the start date of the surgery appointment.
     * @param startTime     the start time of the surgery appointment.
     * @param endDate       the end date of the surgery appointment.
     * @param endTime       the end time of the surgery appointment.
     * @param priority      the priority level of the surgery appointment.
     * @throws SniffException if any details are of the wrong format.
     */
    public SurgeryCommand(String animalType, String animalName, String ownerName,
                          String contactNumber, LocalDate startDate, LocalTime startTime,
                          LocalDate endDate, LocalTime endTime, String priority) throws SniffException {
        this.priority = priority;
        this.animal = new Animal(animalType, animalName);
        this.owner = new Owner(ownerName, contactNumber);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    /**
     * Executes add surgery method located in SniffTasks class
     *
     * @param tasks The SniffTasks Class
     */
    @Override
    public void executeCommand(SniffTasks tasks) throws SniffException {
        tasks.addSurgery(animal, owner, priority, startDate, startTime, endDate, endTime);
    }
}
