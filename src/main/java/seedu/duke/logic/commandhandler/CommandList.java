package seedu.duke.commandhandler;

/**
 * Contains all the possible commands that the user can input
 */
public interface CommandList {
    String GENERATE_COMMAND = "generate";
    String HELP_COMMAND = "help";
    String FILTERS_COMMAND = "filters";
    String EXIT_COMMAND = "exit";
    String BYE_COMMAND = "bye";
    String READ_SAMPLE_COMMAND = "readSample";
    String WRITE_SAMPLE_COMMAND = "writeSample";
    String START_COMMAND = "start";
    String CURRENT_COMMAND = "current";
    String FINISH_COMMAND = "finish";
    String CANCEL_COMMAND = "cancel";
    String HISTORY_COMMAND = "history";
    String FIND_COMMAND = "find";
    String PLANNER_EDITOR_COMMAND = "planner";
    String VIEW_PLAN_COMMAND = "plans";
    String QUICK_START_COMMAND = "quick";
    String DELETE_PLAN_COMMAND = "delete";
    String ADD_PLAN_COMMAND = "add";
    String EXERCISE_DATA_COMMAND = "data";
}
