package functionalities.appointments;

import functionalities.Animal;
import functionalities.Owner;

public abstract class Appointment {

    public String uid;
    public Animal animal;
    public boolean isDone;
    protected Owner owner;

    public Appointment(String uid, Animal animal, Owner owner) {
        this.uid = uid;
        this.animal = animal;
        this.owner = owner;
    }

    public abstract String toString();

    public abstract String getDescription();

    public String getStatus(){
        return (isDone ? "X" : " ");
    }

    public void setIsDone(boolean status){
        this.isDone = status;
    }

}
