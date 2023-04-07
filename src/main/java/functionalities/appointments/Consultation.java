package functionalities.appointments;

import functionalities.Animal;
import functionalities.Owner;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The Consultation class represents a consultation type appointment for an animal with its owner.
 * It also includes the date and time of the appointment.
 * This class extends the abstract Appointment class.
 */
public class Consultation extends Appointment {

    protected LocalDate date;
    protected LocalTime time;

    protected String description = "consultation";

    public Consultation(String uid, Animal animal, Owner owner, LocalDate date, LocalTime time) {
        super(uid, animal, owner);
        this.uid = uid;
        this.animal = animal;
        this.owner = owner;
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

    public String retrieveStorageInfo() {
        return uid + " | " + date + " | " + time + " | " + animal.getAnimalName() + " | " + animal.getAnimalType()
                + " | " + owner.getName() + " | " + owner.getContactNumber();
    }

    @Override
    public String getDescription() {
        return description;
    }
}
