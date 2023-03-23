package seedu.ui;

public class CalorieUi extends GeneralUi {

    public void requestCalorieLimit() {
        System.out.println("What do you wish to set as the limit of your daily calorie intake (kcal)?");
        System.out.println("Please enter it in below: ");
    }

    public void showCurrentIntake() {
        System.out.println("This is your current calorie intake for today: ");
    }

    @Override
    public void showRemainingIntake() {
        System.out.println("This is the amount of calories that " +
                "you can consume before exceeding your limit for today: ");
    }
    public void showDailyCaloricLimit(){
        System.out.println("This is your daily caloric limit: ");
    }
    public void showWellDoneMessage() {
        System.out.println("Congratulations! Your daily calorie intake for today is within the set limit.");
    }
}
