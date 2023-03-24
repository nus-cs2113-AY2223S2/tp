package seedu.duke.logic.commandhandler;

import seedu.duke.commons.exceptions.DukeError;

import seedu.duke.storage.StorageHandler;
import seedu.duke.ui.Ui;
import seedu.duke.data.userdata.userplan.UserPlan;
import seedu.duke.commons.util.StringSplitter;

import java.util.Scanner;

//@@author Khulon
public class PlannerCommandHandler implements CommandList {
    public static void plannerCommandHandler (Ui ui, UserPlan planner, StorageHandler storageHandler) throws DukeError {
        ui.printPlannerGreeting();
        Scanner in = new Scanner(System.in);

        while (true) {
            String rawUserCommands = in.nextLine();
            StringSplitter stringSplitter = new StringSplitter();
            String[] userCommands = stringSplitter.splitString(rawUserCommands);
            switch (userCommands[0]) {
            case HELP_COMMAND:
                ui.printPlannerHelp();
                break;
            case VIEW_PLAN_COMMAND:
                ui.showPlan(planner);
                break;
            case EXIT_COMMAND:
                System.out.println("Exited planner editor!");
                return;
            case ADD_PLAN_COMMAND:
                UserPlan.addPlan(userCommands);
                break;
            case FILTERS_COMMAND:
                ui.printFilters();
                break;
            case DELETE_PLAN_COMMAND:
                UserPlan.deletePlan(userCommands);
                break;
            default:
                ui.unknownCommand();
            }
            storageHandler.writeToJson(planner);

        }
    }

}
