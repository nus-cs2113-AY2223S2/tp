package seedu.commands;

import java.time.LocalDate;
import seedu.entities.CaloricIntake;
import seedu.entities.User;
import seedu.exceptions.ExtraArgumentsException;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.exceptions.InvalidFieldNameException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;
import seedu.ui.CalorieUi;


public class ViewUserCommand extends Command {
    private String fieldName;
    private String command;
    private String userInput;

    public ViewUserCommand(String command, String userInput){
        this.command = command;
        this.userInput = userInput;
    }

    public boolean isValidFieldName(String fieldName){
        switch(fieldName){
        case "/name":
            return true;
        case "/weight":
            return true;
        case "/height":
            return true;
        case "/age":
            return true;
        case "/gender":
            return true;
        case "/caloricLimit":
            return true;
        case "/caloriesLeft":
            return true;
        case "/targetWeight":
            return true;
        default:
            return false;
        }
    }
    public void parseCommand() throws LifeTrackerException{
        String[] userInputSplit = userInput.split(" ");
        if(command.length() == userInput.length() || userInputSplit.length < 2){
            throw new MissingArgumentsException(command, "[fieldName]");
        } else if (userInputSplit.length > 2) {
            throw new ExtraArgumentsException();
        }
        fieldName = userInputSplit[1];

        if(!isValidFieldName(fieldName)){
            throw new InvalidFieldNameException(command, fieldName);
        }
    }

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
                        ExerciseStorage exerciseStorage)
            throws LifeTrackerException {
        User user = userStorage.getUser();
        this.parseCommand();
        CalorieUi calorieUi = new CalorieUi();
        CaloricIntake meals = new CaloricIntake(mealStorage.getMealByDate(LocalDate.now()));
        switch (fieldName) {
        case "/name":
            String name = user.getName();
            if(name.isBlank()){
                ui.printFieldNotStored();
            }else{
                ui.printName(name);
            }
            break;
        case "/weight":
            float weight = user.getWeight();
            if(weight == 0.0){
                ui.printFieldNotStored();
            }else {
                ui.printWeight(weight);
            }
            break;
        case "/height":
            float height = user.getHeight();
            if(height == 0.0){
                ui.printFieldNotStored();
            }else {
                ui.printHeight(height);
            }
            break;
        case "/age":
            int age = user.getAge();
            if(age == 0){
                ui.printFieldNotStored();
            }else{
                ui.printAge(age);
            }
            break;
        case "/gender":
            String gender = user.getGender();
            if(gender.isBlank()){
                ui.printFieldNotStored();
            }else {
                ui.printGender(gender);
            }
            break;
        case "/caloricLimit":
            double caloricLimit = user.getCaloricLimit();
            calorieUi.showDailyCaloricLimit(caloricLimit);
            break;
        case "/caloriesLeft":
            double calorieIntake = meals.getTotalDailyCalories();
            double caloriesLeft = user.getCaloriesLeft(calorieIntake);
            calorieUi.showRemainingIntake(caloriesLeft);
            break;
        case "/targetWeight":
            float targetWeight = user.getTargetWeight();
            System.out.println("Target Weight: " + targetWeight + " kg");
            break;
        default:
            break;
        }
    }
}
