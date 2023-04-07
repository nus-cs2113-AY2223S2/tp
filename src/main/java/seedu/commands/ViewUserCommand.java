package seedu.commands;

import java.time.LocalDate;
import seedu.entities.CaloricIntake;
import seedu.entities.User;
import seedu.exceptions.ExtraArgumentsException;
import seedu.exceptions.InvalidChoiceException;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;
import seedu.ui.CalorieUi;


public class ViewUserCommand extends Command {
    private int number;
    private String command;
    private String userInput;

    public ViewUserCommand(String command, String userInput){
        this.command = command;
        this.userInput = userInput;
    }

    public void parseCommand() throws LifeTrackerException{
        String[] userInputSplit = userInput.split(" ");
        if(command.length() == userInput.length() || userInputSplit.length < 2){
            throw new MissingArgumentsException(command, "[number]");
        } else if (userInputSplit.length > 2) {
            throw new ExtraArgumentsException();
        }
        number = Integer.parseInt(userInputSplit[1]);
    }

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
                        ExerciseStorage exerciseStorage)
            throws LifeTrackerException {
        User user = userStorage.getUser();
        this.parseCommand();
        CalorieUi calorieUi = new CalorieUi();
        CaloricIntake meals = new CaloricIntake(mealStorage.getMealByDate(LocalDate.now()));
        switch (number) {
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
        default:
            throw new InvalidChoiceException();
        }
    }
}
