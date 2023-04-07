package seedu.commands;

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

public class UpdateUserCommand extends Command {
    private int number;
    private String command;
    private String userInput;

    public UpdateUserCommand(String command, String userInput){
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
        switch (number) {
        case 1:
            user.setName(updateName(ui));
            break;
        case 2:
            user.setWeight(updateWeight(ui));
            user.setCaloricLimit(
                User.calculateCaloricNeeds(user.getWeight(), user.getHeight(), user.getAge(), user.getGender())
            );
            User.displayNewWeightDifference(user.getWeight(), user.getTargetWeight());
            break;
        case 3:
            user.setHeight(updateHeight(ui));
            user.setCaloricLimit(
                User.calculateCaloricNeeds(user.getWeight(), user.getHeight(), user.getAge(), user.getGender())
            );
            break;
        case 4:
            user.setAge(updateAge(ui));
            user.setCaloricLimit(
                User.calculateCaloricNeeds(user.getWeight(), user.getHeight(), user.getAge(), user.getGender())
            );
            break;
        case 5:
            user.setGender(updateGender(ui));
            user.setCaloricLimit(
                User.calculateCaloricNeeds(user.getWeight(), user.getHeight(), user.getAge(), user.getGender())
            );
            break;
        case 6:
            user.setTargetWeight(updateTargetWeight(ui));
            User.displayNewTargetWeightDifference(user.getWeight(), user.getTargetWeight());
            break;
        default:
            throw new InvalidChoiceException();
        }
        
        userStorage.updateUser(user);
    }

    public String updateName(GeneralUi ui) {
        String nameString = null;
        while (true) {
            boolean containsNum = false;
            System.out.println("Enter new name: ");
            nameString = ui.readLine();
            for (int i = 0; i < nameString.length(); ++i) {
                if (Character.isDigit(nameString.charAt(i))) {
                    containsNum = true;
                    break;
                }
            }
            if (nameString.isBlank() || containsNum) {
                System.out.println("Invalid name format!");
            }else {
                break;
            }
        }
        return nameString;
    }
    public float updateWeight(GeneralUi ui) {
        String weightString = "dummy";
        while (true) {
            System.out.println("Enter new weight: ");

            weightString = ui.readLine();
            if (weightString.matches("[+-]?([0-9]*[.])?[0-9]+")) {
                break;
            }
            System.out.println("Invalid weight format!");
        }
        float weight = Float.parseFloat(weightString);
        assert weight >= 0: "Invalid weight";
        return weight;
    }

    public float updateHeight(GeneralUi ui) {
        String heightString = "dummy";
        while (true) {
            System.out.println("Enter new height: ");
            heightString = ui.readLine();
            if (heightString.matches("[+-]?([0-9]*[.])?[0-9]+")) {
                break;
            }
            System.out.println("Invalid height format!");
        }
        float height = Float.parseFloat(heightString);
        assert height >= 0: "Invalid height";
        return height;
    }

    // bug in updating age
    public int updateAge(GeneralUi ui){
        String ageString = "dummy";
        while(true){
            System.out.println("Enter new age: ");
            ageString = ui.readLine();
            if(ageString.matches("[+-]?([0-9]*[.])?[0-9]+")){
                break;
            }
            System.out.println("Invalid age format!");
        }
        int age = Integer.parseInt(ageString);
        assert age >= 0: "Invalid Age";
        return age;
    }

    public String updateGender(GeneralUi ui) {
        String genderString = "dummy";
        while (true) {
            System.out.println("Enter new gender: ");
            genderString = ui.readLine();
            // bug in updating gender

            if (!genderString.equalsIgnoreCase("male") | !genderString.equalsIgnoreCase("female") ) {
                break;
            }
            System.out.println("Invalid gender format!");
        }
        return genderString;
    }

    public float updateTargetWeight(GeneralUi ui) {
        String targetWeightString = "dummy";
        while (true) {
            System.out.println("Enter new target weight: ");
            targetWeightString = ui.readLine();
            if(targetWeightString.matches("[+-]?([0-9]*[.])?[0-9]+")) {
                break;
            }
            System.out.println("Invalid target weight format");
        }
        float targetWeight = Float.parseFloat(targetWeightString);
        assert targetWeight >= 0: "Invalid target weight";
        return targetWeight;
    }
}
