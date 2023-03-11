package seedu.Entities;

public class User {
    private String name;
    private float weight;
    private float height;

    public User() {
        this.name = "";
        this.weight = 0;
        this.weight = 0;
    }

    public User(String name, float weight, float height) {
        this.name = name;
        this.weight = weight;
        this.height = height;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String[] toWriteFormat() {
        String weight = Float.toString(this.weight);
        String height = Float.toString(this.height);
        String[] value = { this.name, weight, height };
        
        return value;
    }
}
