package functionalities.appointments;

import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;

/**
 * The Appointment class represents an appointment for an animal with its owner.
 * This class is abstract and provides methods for retrieving and setting appointment information.
 */
public abstract class Appointment {

    public String uid;
    public Animal animal;
    public boolean isDone;
    protected Owner owner;

    /**
     * Constructs an appointment with the specified unique identifier, animal, and owner.
     *
     * @param uid    The unique identifier for the appointment.
     * @param animal The animal associated with the appointment.
     * @param owner  The owner of the animal associated with the appointment.
     */
    public Appointment(String uid, Animal animal, Owner owner) {
        this.uid = uid;
        this.animal = animal;
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public abstract String toString();

    public abstract boolean isDate(String date) throws SniffException;

    public abstract String getDescription();

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public abstract String retrieveStorageInfo();

    /**
     * Sets the status of the appointment.
     *
     * @param status The status of the appointment.
     */
    public void setIsDone(boolean status) {
        this.isDone = status;
    }
}
