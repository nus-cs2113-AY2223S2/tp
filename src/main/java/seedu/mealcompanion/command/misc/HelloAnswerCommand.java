package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;

//@@author Jjzeng123
public class HelloAnswerCommand extends ExecutableCommand {
    public void execute(MealCompanionSession mealCompanionSession) {
        mealCompanionSession.getUi().printMessage("Congratulations! You have solved our puzzle and unlocked our "
                + "hidden easter egg!");
        mealCompanionSession.getUi().printMessage("This easter egg is dedicated to our amazing CS2101 Professor, "
                + "Professor Serene Yeo! We hope you enjoy this light read!");
        mealCompanionSession.getUi().printMessage("https://coconuts.co/singapore/news/singaporean-mother-complains-"
                + "to-education-minister-over-unreasonably-tough-mathematics-psle-paper/");
        mealCompanionSession.getUi().printMessage("We hope you have enjoyed using Meal Companion and we hope "
                + "this easter egg has been fun!");

    }
}
