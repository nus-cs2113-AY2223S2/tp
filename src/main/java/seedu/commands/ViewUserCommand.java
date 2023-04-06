package seedu.commands;

import java.time.LocalDate;
import seedu.entities.CaloricIntake;
import seedu.entities.User;
import seedu.exceptions.LifeTrackerException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;
import seedu.ui.CalorieUi;


public class ViewUserCommand extends Command {
    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
                        ExerciseStorage exerciseStorage)
            throws LifeTrackerException {
        User user = userStorage.getUser();
        CalorieUi calorieUi = new CalorieUi();
        CaloricIntake meals = new CaloricIntake(mealStorage.getMealByDate(LocalDate.now()));
        int choice;
        boolean toContinue = true;

        while (toContinue) {
            System.out.println("View user settings");
            System.out.println("1. View Name");
            System.out.println("2. View Weight");
            System.out.println("3. View Height");
            System.out.println("4. View Age");
            System.out.println("5. View Gender");
            System.out.println("6. View Daily Caloric limit");
            System.out.println("7. View Calories Remaining Today");
            System.out.println("8. View Target Weight");
            System.out.println("9. Exit");
            System.out.println();

            choice = ui.readInt();

            switch (choice) {
            case 1:
                String name = user.getName();
                if(name.isBlank()){
                    ui.printFieldNotStored();
                }else{
                    ui.printName(name);
                }
                break;
            case 2:
                float weight = user.getWeight();
                if(weight == 0.0){
                    ui.printFieldNotStored();
                }else {
                    ui.printWeight(weight);
                }
                break;
            case 3:
                float height = user.getHeight();
                if(height == 0.0){
                    ui.printFieldNotStored();
                }else {
                    ui.printHeight(height);
                }
                break;
            case 4:
                int age = user.getAge();
                if(age == 0){
                    ui.printFieldNotStored();
                }else{
                    ui.printAge(age);
                }
                break;
            case 5:
                String gender = user.getGender();
                if(gender.isBlank()){
                    ui.printFieldNotStored();
                }else {
                    ui.printGender(gender);
                }
                break;
            case 6:
                double caloricLimit = user.getCaloricLimit();
                calorieUi.showDailyCaloricLimit(caloricLimit);
                break;
            case 7:
                double calorieIntake = meals.getTotalDailyCalories();
                double caloriesLeft = user.getCaloriesLeft(calorieIntake);
                calorieUi.showRemainingIntake(caloriesLeft);
                break;
            case 8:
                float targetWeight = user.getTargetWeight();
                System.out.println("Target Weight: " + targetWeight + " kg");
                break;
            case 9:
                break;
            default:
                System.out.println("Invalid Choice!");
            }

            System.out.println(System.lineSeparator() + "Continue viewing?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println();

            choice = ui.readInt();

            switch (choice) {
            case 1:
                toContinue = true;
                break;
            case 2:
                toContinue = false;
                break;
            default:
                toContinue = false;
                System.out.println("Invalid Choice! Exiting...");
            }
        }
    }
}
