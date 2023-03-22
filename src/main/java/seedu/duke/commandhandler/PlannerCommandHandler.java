package seedu.duke.commandhandler;

import seedu.duke.ui.Ui;
import seedu.duke.userplan.UserPlan;

import java.util.Scanner;

public class PlannerCommandHandler {
    //@@author Khulon
    public static void plannerCommandHandler (Ui ui, UserPlan planner) {
        System.out.println("Plan your Workouts here!! try:");
        System.out.println("Add Monday Home_Leg_Day legs static");
        System.out.println("Delete Monday Home_Leg_Day");
        Scanner in = new Scanner(System.in);

        while (true) {
            String rawUserCommands = in.nextLine();
            String[] userCommands = rawUserCommands.split(" ");
            switch (userCommands[0]) {
            case "help":
                ui.printPlannerHelp();
                break;
            case "plan":
                ui.showPlan(planner);
                break;
            case "exit":
                System.out.println("Exited planner editor!");
                return;
            case "add":
                //need to add exception for noname and filters, available filters
                UserPlan.addPlan(userCommands);
                break;
            case "delete":
                UserPlan.deletePlan(userCommands);
                break;
            default:
                ui.unknownCommand();
            }
        }
    }

}
