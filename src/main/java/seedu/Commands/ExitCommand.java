package seedu.Commands;

import seedu.Database.FoodStorage;
import seedu.Database.UserStorage;
import seedu.Exceptions.LifeTrackerException;

public class ExitCommand extends Command{
    @Override
    public void execute(FoodStorage foodList, UserStorage userList) throws LifeTrackerException {
        setIsExit(true);
    }
}
