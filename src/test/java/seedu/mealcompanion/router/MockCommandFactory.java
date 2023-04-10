package seedu.mealcompanion.router;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.parser.CommandArguments;

class MockCommandFactory extends ExecutableCommandFactory {
    private String expectedRoute;

    public MockCommandFactory(String expectedRoute) {
        this.expectedRoute = expectedRoute;
    }

    public ExecutableCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments args) {
        throw new UnsupportedOperationException("MockCommandFactory is for testing routing only!");
    }

    @Override
    public String getCommandFormat() {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof String)) {
            return false;
        }
        return this.expectedRoute.equals(other);
    }
}
