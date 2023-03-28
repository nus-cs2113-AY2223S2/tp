package functionalities.appointments;

import functionalities.Animal;
import functionalities.Owner;

public abstract class Appointment {

    public String uid;
    public Animal animal;
    protected Owner owner;
    public boolean isDone;

    public Appointment(String uid, Animal animal, Owner owner, boolean isDone) {
        this.uid = uid;
        this.animal = animal;
        this.owner = owner;
        this.isDone = false;
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
