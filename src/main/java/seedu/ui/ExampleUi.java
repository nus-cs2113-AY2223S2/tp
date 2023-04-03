package seedu.ui;

import seedu.database.ExampleData;

public class ExampleUi extends GeneralUi{

    public void showExerciseExamples() {
        System.out.println("Here are some examples of exercises:");
    }

    public void showMealExamples() {
        System.out.println("Here are some examples of meals that you can consider:");
    }

    public void afterExercisesMessage() {
        printLine();
        System.out.println("Hope this gives you some idea on the exercises that you can do!");
    }

    public void afterMealsMessage() {
        printLine();
        System.out.println("Remember to eat in moderation!");
    }
    public void displayExerciseExamples() {
        String[] exerciseExampleData = ExampleData.getExampleExerciseData();
        showExerciseExamples();
        printLine();
        for (String exercise: exerciseExampleData) {
            String[] exerciseInfo = exercise.split(",");
            System.out.println(exerciseInfo[0].substring(0) + ": " + exerciseInfo[1] + ", " + exerciseInfo[2]);
        }
        afterExercisesMessage();
    }

    public void displayMealExamples() {
        String[] mealExampleData = ExampleData.getExampleMealData();
        showMealExamples();
        printLine();
        for (String meal: mealExampleData) {
            String[] mealInfo = meal.split(",");
            System.out.println(mealInfo[0].substring(0));
        }
        afterMealsMessage();
    }
}
