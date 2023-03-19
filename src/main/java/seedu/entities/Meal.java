package seedu.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import seedu.constants.DateConstants;
import seedu.definitions.MealTypes;

public class Meal implements Comparable<Meal> {
    private ArrayList<Food> foods;
    private LocalDate date;
    private double totalCalories;
    private MealTypes identifier;

    public Meal(ArrayList<Food> foods, LocalDate date, MealTypes identifier) {
        this.foods = foods;
        this.date = date;
        this.identifier = identifier;
        calculateTotalCalories();
    }

    public ArrayList<Food> getFoods() {
        return this.foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotalCalories() {
        return this.totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    public MealTypes getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(MealTypes identifier) {
        this.identifier = identifier;
    }


    public void calculateTotalCalories() {
        for (Food food : foods) {
            this.totalCalories += food.getCalories();
        }
    }

    public String[] toWriteFormat(String delimiter, String dateFormat) {
        String[] output = new String[3];
        output[0] = date.format(DateTimeFormatter.ofPattern(dateFormat, Locale.ENGLISH));
        String[] foodArray = new String[foods.size()];
        for (int i = 0; i < foods.size(); i++) {
            foodArray[i] = String.valueOf(foods.get(i).getId());
        }
        output[1] = String.join(delimiter, foodArray);
        output[2] = this.identifier.toString();
        return output;
    }

    @Override
    public String toString() {
        String output = this.identifier + " was consumed on " + 
                date.format(DateTimeFormatter.ofPattern(DateConstants.PARSE_FORMAT, Locale.ENGLISH)) + 
                System.lineSeparator();
        output += "Total Calories are: " + this.totalCalories + System.lineSeparator();
        output += "Here are the foods you ate:" + System.lineSeparator();
        for (int i = 0; i < foods.size(); i++) {
            output += (i+1) + ") " + foods.get(i).toString() + System.lineSeparator();
        }
        return output;
    }

    @Override
    public int compareTo(Meal otherMeal) {
        if (getDate() == null || otherMeal.getDate() == null) {
            return 0;
        }
        int comparator = this.getDate().compareTo(otherMeal.getDate());
        if (comparator != 0) {
            return comparator;
        } else {
            return this.getIdentifier().isBefore(otherMeal.getIdentifier());
        }
    }
}
