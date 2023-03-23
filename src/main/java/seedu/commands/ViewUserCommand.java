package seedu.commands;

import seedu.database.ExerciseStorage;
import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.database.UserStorage;
import seedu.entities.CaloricIntake;
import seedu.entities.User;
import seedu.exceptions.LifeTrackerException;
import seedu.ui.GeneralUi;
import seedu.ui.CalorieUi;


public class ViewUserCommand extends Command {

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
                        ExerciseStorage exerciseStorage)
            throws LifeTrackerException {
        User user = userStorage.getUser();
        CalorieUi calorieUi = new CalorieUi();
        CaloricIntake meals = new CaloricIntake(mealStorage.getMeal());
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
            System.out.println("7. View Calories Left Today");
            System.out.println("8. Back");
            System.out.println();

            choice = ui.readInt();

            switch (choice) {
            case 1:
                String name = user.getName();
                System.out.println("Name: " + name);
                break;
            case 2:
                float weight = user.getWeight();
                System.out.println("Weight: " + weight + " kg");
                break;
            case 3:
                float height = user.getHeight();
                System.out.println("Height: " + height + " cm");
                break;
            case 4:
                int age = user.getAge();
                System.out.println("Age: " + age + " years old");
                break;
            case 5:
                String gender = user.getGender();
                System.out.println("Gender: " + gender);
                break;
            case 6:
                double caloricLimit = user.getCaloricLimit();
                calorieUi.showDailyCaloricLimit();
                System.out.println(caloricLimit + " Kcal");
                break;
            case 7:
                double calorieIntake = meals.getTotalDailyCalories();
                double caloriesLeft = user.getCaloriesLeft(calorieIntake);
                calorieUi.showRemainingIntake();
                System.out.println(caloriesLeft + " Kcal");
                break;
            case 8:
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
