package functionalities.appointments;

import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;

import java.time.LocalDate;
import java.time.LocalTime;

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

    public Vaccination(String uid, Animal animal, Owner owner, LocalDate date, LocalTime time,
                       String vaccine) throws SniffException{
        super(uid, animal, owner);
        this.uid = uid;
        this.animal = animal;
        this.owner = owner;
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

    public String setVaccine(String vaccine) throws SniffException{
        if (vaccine.isBlank()) {
            throw new SniffException(" Vaccine cannot be empty!");
        }
        return vaccine;
    }
}
