package seedu.Entities;

public class User {
    private String name;
    private float weight;
    private float height;
    private int age;

    public User() {
        this.name = "";
        this.weight = 0;
        this.height = 0;
        this.age = 0;
    }

    public User(String name, float weight, float height, int age) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.age = age;
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

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getCaloricNeeds (float weight, float height) {
        double calories = (88.4 + (13.4 * weight)) + (4.8 * height) - (5.68 * age);
        //assuming it's a dude
        return calories;
    }
    public String[] toWriteFormat() {
        String weight = Float.toString(this.weight);
        String height = Float.toString(this.height);
        String[] value = { this.name, weight, height };
        
        return value;
    }
}
