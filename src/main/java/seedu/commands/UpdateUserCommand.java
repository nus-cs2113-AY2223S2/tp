package seedu.commands;

import seedu.entities.User;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.InvalidFieldNameException;
import seedu.exceptions.InvalidFieldInfoFormatException;
import seedu.exceptions.NegativeFieldInfoException;
import seedu.exceptions.MissingArgumentsException;
import seedu.exceptions.ExtraArgumentsException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

public class UpdateUserCommand extends Command {
    private String fieldName;
    private String command;
    private String userInput;
    private String updatedInfo;

    public UpdateUserCommand(String command, String userInput){
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
        case "/targetWeight":
            return true;
        default:
            return false;
        }
    }

    public void parseCommand() throws LifeTrackerException{
        String[] userInputSplit = userInput.split(" ");
        if(command.length() == userInput.length() || userInputSplit.length < 3){
            throw new MissingArgumentsException(command, "[user information field]/ [new information]");
        } else if (userInputSplit.length > 3) {
            throw new ExtraArgumentsException();
        }
        fieldName = userInputSplit[1];
        updatedInfo = userInputSplit[2];
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
        switch (fieldName){
        case "/name":
            user.setName(updateName());
            break;
        case "/weight":
            user.setWeight(updateWeight());
            user.setCaloricLimit(
                User.calculateCaloricNeeds(user.getWeight(), user.getHeight(), user.getAge(), user.getGender())
            );
            User.displayNewWeightDifference(user.getWeight(), user.getTargetWeight());
            break;
        case "/height":
            user.setHeight(updateHeight());
            user.setCaloricLimit(
                User.calculateCaloricNeeds(user.getWeight(), user.getHeight(), user.getAge(), user.getGender())
            );
            break;
        case "/age":
            user.setAge(updateAge());
            user.setCaloricLimit(
                User.calculateCaloricNeeds(user.getWeight(), user.getHeight(), user.getAge(), user.getGender())
            );
            break;
        case "/gender":
            user.setGender(updateGender());
            user.setCaloricLimit(
                User.calculateCaloricNeeds(user.getWeight(), user.getHeight(), user.getAge(), user.getGender())
            );
            break;
        case "/targetWeight":
            user.setTargetWeight(updateTargetWeight());
            User.displayNewTargetWeightDifference(user.getWeight(), user.getTargetWeight());
            break;
        default:
            break;
        }
        userStorage.updateUser(user);
    }

    public String updateName() throws LifeTrackerException{
        String nameString = null;
        boolean containsNum = false;
        nameString = updatedInfo;
        for (int i = 0; i < nameString.length(); ++i) {
            if (Character.isDigit(nameString.charAt(i))) {
                containsNum = true;
                break;
            }
        }
        if (nameString.isBlank() || containsNum) {
            throw new InvalidFieldInfoFormatException(fieldName, updatedInfo);
        }else {
            return nameString;
        }
    }
    public float updateWeight() throws LifeTrackerException {
        String weightString = "dummy";

        weightString = updatedInfo;
        if (!weightString.matches("[+-]?([0-9]*[.])?[0-9]+")) {
            throw new InvalidFieldInfoFormatException(fieldName, updatedInfo);
        }
        float weight = Float.parseFloat(weightString);
        if(weight < 0){
            throw new NegativeFieldInfoException(command, fieldName);
        }
        return weight;
    }

    public float updateHeight() throws LifeTrackerException {
        String heightString = "dummy";
        heightString = updatedInfo;
        if (!heightString.matches("[+-]?([0-9]*[.])?[0-9]+")) {
            throw new InvalidFieldInfoFormatException(fieldName, updatedInfo);
        }

        float height = Float.parseFloat(heightString);
        if(height < 0){
            throw new NegativeFieldInfoException(command, fieldName);
        }
        return height;
    }

    public int updateAge() throws LifeTrackerException{
        String ageString = "dummy";

        ageString = updatedInfo;
        if(!ageString.matches("[+-]?([0-9]*[.])?[0-9]+")){
            throw new InvalidFieldInfoFormatException(fieldName, updatedInfo);
        }

        int age = Integer.parseInt(ageString);
        if(age < 0){
            throw new NegativeFieldInfoException(command, fieldName);
        }
        return age;
    }

    public String updateGender() throws LifeTrackerException {
        String genderString = "dummy";

        genderString = updatedInfo;
        if (genderString.equalsIgnoreCase("male") | genderString.equalsIgnoreCase("female") ) {
            return genderString;
        }else{
            throw new InvalidFieldInfoFormatException(fieldName, updatedInfo);
        }
    }

    public float updateTargetWeight() throws LifeTrackerException {
        String targetWeightString = "dummy";

        targetWeightString = updatedInfo;
        if(!targetWeightString.matches("[+-]?([0-9]*[.])?[0-9]+")) {
            throw new InvalidFieldInfoFormatException(fieldName, updatedInfo);
        }


        float targetWeight = Float.parseFloat(targetWeightString);
        if(targetWeight < 0){
            throw new NegativeFieldInfoException(command, fieldName);
        }
        return targetWeight;
    }
}
