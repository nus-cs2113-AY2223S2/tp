package functionalities.appointments;

import functionalities.Animal;
import functionalities.Owner;

import java.time.LocalDate;
import java.time.LocalTime;

public class Vaccination extends Appointment {
    protected LocalDate date;
    protected LocalTime time;
    protected String vaccine;
    protected String description = "vaccination";

    public Vaccination(String uid, Animal animal, Owner owner, LocalDate date, LocalTime time, String vaccine,boolean isDone) {
        super(uid, animal, owner, isDone);
        this.uid = uid;
        this.animal = animal;
        this.owner = owner;
        this.date = date;
        this.time = time;
        this.vaccine = vaccine;
    }

    @Override
    public String toString() {
        return " UID: " + uid + " [" + getStatus() +"]"+" | vaccine: " + vaccine + '\n'
                + " Date: " + date + " | Time: " + time + '\n'
                + " Animal Name: " + animal.toString() + '\n'
                + " Owner Name: " + owner.toString();
    }

    @Override
    public String getDescription() {
        return description;
    }
}
