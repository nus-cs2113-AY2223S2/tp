package functionalities.appointments;

import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Consultation class represents a consultation type appointment for an animal with its owner.
 * It also includes the date and time of the appointment.
 * This class extends the abstract Appointment class.
 */
public class Consultation extends Appointment {

    protected LocalDate date;
    protected LocalTime time;

    protected String description = "consultation";

    /**
     * Constructs a new Consultation object with the specified parameters.
     * @param uid The unique identifier for the consultation appointment.
     * @param animal The animal being treated in the consultation appointment.
     * @param owner The owner of the animal being treated.
     * @param date The date of the consultation appointment.
     * @param time The time of the consultation appointment.
     */
    public Consultation(String uid, Animal animal, Owner owner, LocalDate date, LocalTime time) {
        super(uid, animal, owner);
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return " UID: " + uid + " [" + getStatus() + "]" + '\n'
                + " Date: " + date + " | Time: " + time + '\n'
                + " Animal Name: " + animal.toString() + '\n'
                + " Owner Name: " + owner.toString();
    }

    @Override
    public boolean isDate(String dateDetails) throws SniffException {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate userDate = LocalDate.parse(dateDetails, dateFormatter);
            return userDate.equals(date);
        } catch (DateTimeParseException d) {
            throw new SniffException(" Invalid Date format for find command!");
        }
    }

    public String retrieveStorageInfo() {
        return uid + " | " + date + " | " + time + " | " + animal.getAnimalName() + " | " + animal.getAnimalType()
                + " | " + owner.getName() + " | " + owner.getContactNumber();
    }

    @Override
    public String getDescription() {
        return description;
    }
}
