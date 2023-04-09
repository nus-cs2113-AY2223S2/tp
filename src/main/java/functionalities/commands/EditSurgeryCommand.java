package functionalities.commands;

import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;
import functionalities.SniffTasks;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Command to execute EditSurgery functionality
 */
public class EditSurgeryCommand extends Command{
    public String uid;
    private final Animal animal;
    private final Owner owner;
    private final String priority;
    private final LocalDate startDate;
    private final LocalTime startTime;
    private final LocalDate endDate;
    private final LocalTime endTime;

    public EditSurgeryCommand(String uid,String animalType, String animalName, String ownerName,
                          String contactNumber, LocalDate startDate, LocalTime startTime,
                          LocalDate endDate, LocalTime endTime, String priority) throws SniffException {
        this.uid = uid;
        this.priority = priority;
        this.animal = new Animal(animalType, animalName);
        this.owner = new Owner(ownerName, contactNumber);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }
    /**
     * Executes EditSurgery Command method located in SniffTasks class
     * @param tasks The SniffTasks Class
     * @throws SniffException thrown when the input does not follow the format
     */
    @Override
    public void executeCommand(SniffTasks tasks) throws SniffException {
        tasks.editSurgery(uid,animal, owner, priority, startDate, startTime, endDate, endTime);
    }
}
