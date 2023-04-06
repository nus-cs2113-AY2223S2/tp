package seedu.entities;

import java.util.Objects;

import seedu.logger.LogFileHandler;

public class User {
    private String name;
    private float weight;
    private float height;
    private int age;
    private String gender;
    private float targetWeight;
    private double caloricLimit;
    
    public User() {
        this.name = "";
        this.weight = 0;
        this.height = 0;
        this.age = 0;
        this.gender = "";
        this.caloricLimit = 0;
        this.targetWeight = 0;
    }

    public User(String name, float weight, float height, int age, String gender, float targetWeight) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        this.targetWeight = targetWeight;
        this.caloricLimit = calculateCaloricNeeds(weight, height, age, gender);
    }

    public String getName() {
        return this.name;
    }

    public void setCaloricLimit(double caloricLimit) {
        this.caloricLimit = caloricLimit;
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
    public float getTargetWeight() {
        return this.targetWeight;
    }
    public void setTargetWeight(float targetWeight) {
        this.targetWeight = targetWeight;
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

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static void displayNewWeightDifference (float weight, float targetWeight) {
        float actualDifference = weight - targetWeight;
        float difference = 0;
        if (actualDifference > 0) {
            difference = actualDifference;
            System.out.println("You need to lose " + difference + "kg to reach your target weight: " +
                    targetWeight + "kg");
        } else if (actualDifference < 0) {
            difference = actualDifference * (-1);
            System.out.println("You need to gain " + difference + "kg to reach your target weight: " +
                    targetWeight + "kg");
        } else {
            System.out.println("Congrats! You have reached your target weight!");
        }
    }

    public static void displayNewTargetWeightDifference (float weight, float targetWeight) {
        float actualDifference = weight - targetWeight;
        float difference = 0;
        if (actualDifference > 0) {
            difference = actualDifference;
            System.out.println("With your updated target weight, you now need to lose " + difference +
                    "kg to reach it.");
        } else if (actualDifference < 0) {
            difference = actualDifference * (-1);
            System.out.println("With your updated target weight, you now need to gain " + difference +
                    "kg to reach it.");
        } else {
            System.out.println("Congrats! You have reached your target weight!");
        }
    }

    public static double calculateCaloricNeeds (float weight, float height, int age, String gender) {
        double caloricNeeds;
        switch(gender.toLowerCase()) {
        case "male":
            caloricNeeds = 66 + (13.7 * weight) + (5 * height) - (4.7 * age);
            assert caloricNeeds > 0: "Caloric Needs should be more than O";
            return caloricNeeds;
        case "female":
            caloricNeeds = 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
            assert caloricNeeds > 0: "Caloric Needs should be more than O";
            return caloricNeeds;
        default:
            LogFileHandler.logWarning("Gender not provided, cannot calculate caloric needs accurately");
            return 0;
        }
    }

    public double getCaloricLimit() {
        return Math.round(this.caloricLimit);
    }

    public double getCaloriesLeft(double calorieIntake) {
        return Math.round(caloricLimit - calorieIntake);
    }

    public String[] toWriteFormat() {
        String weight = Float.toString(this.weight);
        assert !Objects.equals(weight, "0") : "Weight should be non 0";
        String height = Float.toString(this.height);
        assert !Objects.equals(height, "0"): "Height should be non 0";
        String age = Integer.toString(this.age);
        assert !Objects.equals(age, "0"): "Age should be non 0";
        String targetWeight = Float.toString(this.targetWeight);
        assert !Objects.equals(targetWeight, "0"): "Target Weight should be non 0";
        String[] value = { this.name, weight, height, age, this.gender, targetWeight };
        return value;
    }
}
