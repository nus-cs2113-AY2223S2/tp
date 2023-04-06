package functionalities.appointments;

import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The Surgery class represents a surgery type appointment for an animal with its owner.
 * It also includes the start and end dates and start and end time of the appointment.
 * It also includes the priority level of the surgery.
 * This class extends the abstract Appointment class.
 */
public class Surgery extends Appointment {

    public enum priorityLevel {
        HIGH, MEDIUM, LOW
    }

    protected priorityLevel priority;
    protected LocalDate startDate;
    protected LocalTime startTime;
    protected LocalDate endDate;
    protected LocalTime endTime;
    protected char priorityType;
    protected String description = "surgery";

    public Surgery(String uid, Animal animal, Owner owner,
                   String priority, LocalDate startDate, LocalTime startTime,
                   LocalDate endDate, LocalTime endTime) throws SniffException{
        super(uid, animal, owner);
        this.uid = uid;
        this.animal = animal;
        this.owner = owner;
        this.priorityType = priority.charAt(0);
        this.priority = setPriority(priority);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public priorityLevel setPriority(String priority) throws SniffException{
        if (priority.isBlank()) {
            throw new SniffException(" Priority cannot be empty!");
        }
        switch (priority) {
        case "H":
            return priorityLevel.HIGH;
        case "M":
            return priorityLevel.MEDIUM;
        case "L":
            return priorityLevel.LOW;
        default:
            throw new SniffException(" Priority has to be H, M, L.");
        }
    }

    @Override
    public String toString() {
        return " UID: " + uid + " [" + getStatus() + "]" + " | Priority: " + priority + '\n'
                + " Animal Name: " + animal.toString() + '\n'
                + " Owner Name: " + owner.toString() + '\n'
                + " Start Date: " + startDate + " | Start Time: " + startTime + '\n'
                + " End Date: " + endDate + " | End Time: " + endTime;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String retrieveStorageInfo() {
        return uid + " | " + priorityType + " | " + animal.getAnimalName() + " | " + animal.getAnimalType() + " | "
                + owner.getName() + " | " + owner.getContactNumber() + " | " + startDate + " | " + startTime + " | "
                + endDate + " | " + endTime;
    }
}
