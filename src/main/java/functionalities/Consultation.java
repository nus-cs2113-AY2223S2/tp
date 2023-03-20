package functionalities;

public class Consultation extends Appointment {
    protected String date;
    protected String time;

    public Consultation(String uid, Animal animal, Owner owner, String date, String time) {
        super(uid, animal, owner);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "";
    }
}
