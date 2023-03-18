package seedu.commands;

import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.database.UserStorage;
import seedu.entities.User;
import seedu.exceptions.LifeTrackerException;
import seedu.ui.GeneralUi;

public class UpdateUserCommand extends Command {
    public String updateName(GeneralUi ui) {
        System.out.println("Enter new name: ");
        return ui.readLine();
    }

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage)
            throws LifeTrackerException {
        User user = userStorage.getUser();
        int choice;
        boolean toContinue = true;
        while (toContinue) {
            System.out.println("Update user settings");
            System.out.println("1. Update Name");
            System.out.println("2. Update Weight");
            System.out.println("3. Update Height");
            System.out.println("4. Update Age");
            System.out.println("5. Update Gender");
            System.out.println();

            choice = ui.readInt();

            switch (choice) {
            case 1:
                user.setName(updateName(ui));
                break;
            case 2:
                user.setWeight(updateWeight(ui));
                user.setCaloricLimit(
                    User.calculateCaloricNeeds(user.getWeight(), user.getHeight(), user.getAge(), user.getGender())
                );
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
            default:
                System.out.println("Invalid Choice!");
            }

            System.out.println( System.lineSeparator() + "Continue updating?");
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
        
        userStorage.updateUser(user);
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
        assert weight >= 0: "Invalid height";
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

}
