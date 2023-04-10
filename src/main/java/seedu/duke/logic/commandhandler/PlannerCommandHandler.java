package seedu.duke.logic.commandhandler;

import seedu.duke.commons.exceptions.DukeError;

import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.data.userdata.userplan.UserPlan;
import seedu.duke.commons.util.StringSplitter;

import java.util.Scanner;

//@@author Khulon
public class PlannerCommandHandler implements CommandList {
    public static void plannerCommandHandler (Ui ui, UserPlan planner,
                                              Storage storage, Scanner in) throws DukeError {
        ui.printPlannerGreeting();
        ui.splitLine();

        while (true) {
            String rawUserCommands = in.nextLine();
            StringSplitter stringSplitter = new StringSplitter();
            String[] userCommands = stringSplitter.splitString(rawUserCommands);
            //additional error check for whether there's additional description behind single
            //word commands
            String additionalDescription = "";
            for (int i = 1; i < userCommands.length; i++) {
                additionalDescription = additionalDescription + " " + userCommands[i];
            }
            try {
                switch (userCommands[0]) {
                case HELP_COMMAND:
                    if (additionalDescription.length() != 0) {
                        ui.unknownCommand();
                    } else {
                        ui.printPlannerHelp();
                    }
                    break;
                case VIEW_PLAN_COMMAND:
                    if (additionalDescription.length() != 0) {
                        ui.unknownCommand();
                    } else {
                        ui.showPlan(planner);
                    }
                    break;
                case EXIT_COMMAND:
                    if (additionalDescription.length() != 0) {
                        ui.unknownCommand();
                    } else {
                        System.out.println("Exited planner editor!");
                    }
                    return;
                case ADD_PLAN_COMMAND:
                    UserPlan.addPlan(userCommands);
                    break;
                case FILTERS_COMMAND:
                    if (additionalDescription.length() != 0) {
                        ui.unknownCommand();
                    } else {
                        ui.printFilters();
                    }
                    break;
                case DELETE_PLAN_COMMAND:
                    UserPlan.deletePlan(userCommands);
                    break;
                default:
                    ui.unknownCommand();
                }
                ui.plannerMode();
                ui.splitLine();
                storage.writeToJson(planner);
            } catch (DukeError dukeException) {
                System.out.println(dukeException.getMessage());
            } catch (Exception e) {
                System.out.println("Oops Something went wrong!");
            }

        }
    }

}
