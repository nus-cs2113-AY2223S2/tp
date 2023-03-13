package seedu.Ui;

public class CalorieUi {
    public static void requestCalorieLimit() {
        System.out.println("What do you wish to set as the limit of your daily calorie intake (kcal)?");
        System.out.println("Please enter it in below: ");
    }

    public static void showCurrentIntake() {
        System.out.println("This is your current calorie intake for today: ");
    }

    public static void showRemainingIntake() {
        System.out.println("This is the amount of calories that you can consume before exceeding your limit for today: ");
    }

    public static void showWellDoneMessage() {
        System.out.println("Congratulations! Your daily calorie intake for today is within the set limit.");
    }
}
