package functionalities;

public class Vaccination extends newAppointment{
    protected String date;
    protected String time;
    protected String vaccine;

    public Vaccination(String uid, Animal animal, Owner owner, String date, String time, String vaccine) {
        super(uid, animal, owner);
        this.date = date;
        this.time = time;
        this.vaccine = vaccine;
    }
}
