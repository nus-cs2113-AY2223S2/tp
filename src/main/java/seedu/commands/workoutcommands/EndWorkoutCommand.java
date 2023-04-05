//package seedu.commands.workoutcommands;
//
//
//import seedu.commands.Command;
//
//public class EndWorkoutCommand extends Command {
//    private static final String WORKOUT_COMPLETE_MESSAGE = "Great job completing your workout!";
//    private boolean isEnd = false;
//
//    public EndWorkoutCommand() {
//    }
//
//    public static boolean isEnd() {
//        return isEnd;
//    }
//
//    @Override
//    public String execute() {
//        workoutList.setCurrentWorkoutIndex(WorkoutList.NO_CURRENT_WORKOUT);
//        isEnd = true;
//        return WORKOUT_COMPLETE_MESSAGE;
//    }
//
//
//}
//
