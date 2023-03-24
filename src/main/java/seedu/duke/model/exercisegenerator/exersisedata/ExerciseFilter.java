package seedu.duke.exersisedata;

//@@author Khulon
public class ExerciseFilter {
    private static final String[] filters = {
        "upper",
        "core",
        "legs",
        "gym",
        "static",
        "easy",
        "medium",
        "hard"
    };
    public static boolean isAValidFilter(String filter){
        for (int i = 0; i < filters.length; i ++) {
            if (filter.equals(filters[i])){
                return true;
            }
        }
        return false;
    }
}
