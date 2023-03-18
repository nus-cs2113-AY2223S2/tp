package seedu.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Meal {
    private static final String DATE_FORMAT = "d/M/yyyy";
    private ArrayList<Food> foods;
    private LocalDate date;
    private double totalCalories;

    public Meal(ArrayList<Food> foods, LocalDate date) {
        this.foods = foods;
        this.date = date;
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

    public void calculateTotalCalories() {
        for (Food food : foods) {
            this.totalCalories += food.getCalories();
        }
    }

    public String[] toWriteFormat(String delimiter, String dateFormat) {
        String[] output = new String[2];
        output[0] = date.format(DateTimeFormatter.ofPattern(dateFormat, Locale.ENGLISH));
        String[] foodArray = new String[foods.size()];
        for (int i = 0; i < foods.size(); i++) {
            foodArray[i] = String.valueOf(foods.get(i).getId());
        }
        output[1] = String.join(delimiter, foodArray);
        return output;
    }

    @Override
    public String toString() {
        String output = "Meal was consumed on " + 
                date.format(DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH)) + System.lineSeparator();
        output += "Total Calories are: " + this.getTotalCalories() + System.lineSeparator();
        output += "Here are the foods you ate:" + System.lineSeparator();
        for (int i = 0; i < foods.size(); i++) {
            output += (i+1) + ") " + foods.get(i).toString() + System.lineSeparator();
        }
        return output;
    }
}
