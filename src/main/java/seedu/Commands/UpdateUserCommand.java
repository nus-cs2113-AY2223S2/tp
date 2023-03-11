package seedu.Commands;

import seedu.Database.FoodStorage;
import seedu.Database.MealStorage;
import seedu.Database.UserStorage;
import seedu.Entities.User;
import seedu.Exceptions.LifeTrackerException;
import seedu.Output.UI;

public class UpdateUserCommand extends Command {
    public String updateName(UI ui) {
        System.out.println("Enter new name: ");
        return ui.readLine();
    }

    @Override
    public void execute(UI ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage)
            throws LifeTrackerException {
        User user = userStorage.getUser();
        int choice;
        boolean toContinue = true;
        while (toContinue) {
            System.out.println("Update user settings");
            System.out.println("1. Update Name");
            System.out.println("2. Update Weight");
            System.out.println("3. Update Height");
            System.out.println();

            choice = ui.readInt();

            switch (choice) {
            case 1:
                user.setName(updateName(ui));
                break;
            case 2:
                user.setWeight(updateWeight(ui));
                break;
            case 3:
                user.setHeight(updateHeight(ui));
                break;
            default:
                System.out.println("Invalid Choice!");
            }

            System.out.println("\nContinue updating?");
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

    public float updateWeight(UI ui) {
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

    public float updateHeight(UI ui) {
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
}
