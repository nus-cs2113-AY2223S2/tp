package functionalities.appointments;

import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Surgery class represents a surgery type appointment for an animal with its owner.
 * It also includes the start and end dates and start and end time of the appointment.
 * It also includes the priority level of the surgery.
 * This class extends the abstract Appointment class.
 */
public class Surgery extends Appointment {

    public enum PriorityLevel {
        HIGH, MEDIUM, LOW
    }

    protected PriorityLevel priority;
    protected LocalDate startDate;
    protected LocalTime startTime;
    protected LocalDate endDate;
    protected LocalTime endTime;
    protected char priorityType;
    protected String description = "surgery";

    public Surgery(String uid, Animal animal, Owner owner,
                   String priority, LocalDate startDate, LocalTime startTime,
                   LocalDate endDate, LocalTime endTime) throws SniffException {
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

    public PriorityLevel setPriority(String priority) throws SniffException {
        if (priority.isBlank()) {
            throw new SniffException(" Priority cannot be empty!");
        }
        switch (priority) {
        case "H":
            return PriorityLevel.HIGH;
        case "M":
            return PriorityLevel.MEDIUM;
        case "L":
            return PriorityLevel.LOW;
        default:
            throw new SniffException(" Priority has to be H, M, L.");
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getEndTime() {
        return endTime;
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
    public boolean isDate(String dateDetails) throws SniffException {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate userDate = LocalDate.parse(dateDetails, dateFormatter);
            return (userDate.equals(startDate) || userDate.equals(endDate) ||
                    (userDate.isAfter(startDate) && userDate.isBefore(endDate)));
        } catch (DateTimeParseException d) {
            throw new SniffException(" Invalid Date format for find command!");
        }
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
