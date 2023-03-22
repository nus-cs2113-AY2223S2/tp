package seedu.duke.commandhandler;

import seedu.duke.ui.Ui;
import seedu.duke.userplan.UserPlan;

import java.util.Scanner;

//@@author Khulon
public class PlannerCommandHandler implements CommandList{
    public static void plannerCommandHandler (Ui ui, UserPlan planner) {
        System.out.println("Welcome to planner editor, type exit to exit");
        System.out.println("Plan your Workouts here!! try:");
        System.out.println("Add Monday Home_Leg_Day legs static");
        System.out.println("Delete Monday Home_Leg_Day");
        Scanner in = new Scanner(System.in);

        while (true) {
            String rawUserCommands = in.nextLine();
            String[] userCommands = rawUserCommands.split(" ");
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
                //need to add exception for noname and filters, available filters
                UserPlan.addPlan(userCommands);
                break;
            case DELETE_PLAN_COMMAND:
                UserPlan.deletePlan(userCommands);
                break;
            default:
                ui.unknownCommand();
            }
        }
    }

}
