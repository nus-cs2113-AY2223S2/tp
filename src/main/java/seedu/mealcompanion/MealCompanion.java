package seedu.mealcompanion;
public class MealCompanion {
    /**
     * Main entry-point for the MealCompanion application.
     */
    //@@author EthanYidong
    public static void main(String[] args) {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        mealCompanionSession.runRepl();

    }
}
