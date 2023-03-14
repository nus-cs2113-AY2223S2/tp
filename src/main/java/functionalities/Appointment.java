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

    public String getType() {
        return type;
    }

    public String getAnimal() {
        return animal;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public void setUid(String uid){
        this.uid = uid;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setAnimal(String animal) {
        this.animal = animal;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
