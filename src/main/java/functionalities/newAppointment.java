package functionalities;

public abstract class newAppointment {
    protected String uid;
    protected Animal animal;
    protected Owner owner;
    protected boolean status;

    public newAppointment(String uid, Animal animal, Owner owner) {
        this.uid = uid;
        this.animal = animal;
        this.owner = owner;
        this.status = false;
    }

    public String toString() {
        return "";
    }
}
