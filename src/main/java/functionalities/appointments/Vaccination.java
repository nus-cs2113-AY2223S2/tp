package functionalities.appointments;

import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Vaccination class represents a vaccination type appointment for an animal with its owner
 * and its specific vaccine type. It also includes the date and time of the appointment.
 * This class extends the abstract Appointment class.
 */
public class Vaccination extends Appointment {
    protected LocalDate date;
    protected LocalTime time;
    protected String vaccine;
    protected String description = "vaccination";

    /**
     * Constructs a new Vaccination object with the specified parameters.
     * @param uid The unique identifier for the vaccination appointment.
     * @param animal The animal being treated in the vaccination appointment.
     * @param owner The owner of the animal being treated.
     * @param date The date of the vaccination appointment.
     * @param time The time of the vaccination appointment.
     * @param vaccine The specific type of vaccine for the vaccination appointment.
     */
    public Vaccination(String uid, Animal animal, Owner owner, LocalDate date, LocalTime time,
                       String vaccine) throws SniffException {
        super(uid, animal, owner);
        this.date = date;
        this.time = time;
        this.vaccine = setVaccine(vaccine);
    }

    @Override
    public String toString() {
        return " UID: " + uid + " [" + getStatus() + "]" + " | vaccine: " + vaccine + '\n'
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

    public String getVaccine() {
        return vaccine;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String retrieveStorageInfo() {
        return uid + " | " + vaccine + " | " + date + " | " + time + " | " + animal.getAnimalName() + " | " +
                animal.getAnimalType() + " | " + owner.getName() + " | " + owner.getContactNumber();
    }

    public String setVaccine(String vaccine) throws SniffException {
        if (vaccine.isBlank()) {
            throw new SniffException(" Vaccine description cannot be empty!");
        }
        return vaccine;
    }
}
