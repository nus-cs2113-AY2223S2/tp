package seedu.duke.medicine;
import java.util.Dictionary;
import java.util.Hashtable;

public class Medicine {
    private String name;
    private Dictionary<String, String> medicineDosages = new Hashtable<>();
    private String form;
    private int price;
    public Medicine(String name) {
        this.name = name;
        initialiseMedicineDosages();
    }
    public void initialiseMedicineDosages() {
        medicineDosages.put("Paracetamol", "2 pills 2 times a day");
    }
    public String getDosage() {
        return medicineDosages.get(name);
    }
    @Override
    public String toString() {
        return name;
    }


}
