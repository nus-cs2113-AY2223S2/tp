package functionalities;


public class Appointment {

    protected String uid; //unique identification
    protected String type;
    protected String animal;
    protected String name;
    protected String date;

    public Appointment(String uid, String type, String animal, String name, String date) {
        this.uid = uid;
        this.type = type;
        this.animal = animal;
        this.name = name;
        this.date = date;
    }

    public String toString() {
        return uid + " | " + type + " | " + animal + " | " + name + " | " + date;
    }
}
