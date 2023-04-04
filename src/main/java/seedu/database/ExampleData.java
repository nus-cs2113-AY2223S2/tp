package seedu.database;

public class ExampleData {
    private static final String[] exerciseExampleData = {
        "Weight Lifting: General, 30 minutes workout, 108",
        "Aerobics: water, 30 minutes workout, 144",
        "Stretching, 30 minutes workout, 144",
        "Yoga, 30 minutes workout, 144",
        "Calisthenics: moderate, 30 minutes workout, 162",
        "Frisbee, 30 minutes workout, 108",
        "Volleyball, 30 minutes workout, 108",
        "Water Volleyball, 30 minutes workout, 108",
        "Walking, 30 minutes workout, 133",
        "Badminton, 30 minutes workout, 141",
        "Softball, 30 minutes workout, 180",
        "Dancing, 30 minutes workout, 216",
        "Swimming, 30 minutes workout, 216",
        "Soccer, 30 minutes workout, 252",
        "Rock Climbing, 30 minutes workout, 282",
        "Jogging, 30 minutes workout; 5 mph (12 min/mile), 288",
        "Martial Arts, 30 minutes workout, 360",
        "Running, 30 minutes workout, 10mph (6 min/mile)",
    };

    private static final String[] mealExampleData = {
        "Creamy Chicken Spaghetti (Small) from Western",
        "Alfredo (Small) from Western",
        "Seafood Marinara (Small) from Western",
        "Fresh Mushrooms with Ramen (Regular) from Ramen",
        "Double Mixed Fish Spinach Soup (Regular) from Ramen",
        "Fried Fish Spinach Soup (Regular) from Ramen",
    };

    public static String[] getExampleExerciseData() {
        return exerciseExampleData;
    }

    public static String[] getExampleMealData() {
        return mealExampleData;
    }
}
