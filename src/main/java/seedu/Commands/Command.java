package seedu.Commands;

import seedu.Database.FoodStorage;
import seedu.Database.UserStorage;
import seedu.Exceptions.LifeTrackerException;

public abstract class Command {
    private boolean isExit;

    public Command(){
        setIsExit(false);
    }

    public void setIsExit(boolean exit) {
        this.isExit = exit;
    }

    public abstract void execute(FoodStorage foodList, UserStorage userList) throws LifeTrackerException;
}
