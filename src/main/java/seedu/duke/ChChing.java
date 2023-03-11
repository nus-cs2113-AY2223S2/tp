package seedu.duke;

import seedu.duke.data.ExpenseList;
import java.util.Scanner;

public class ChChing {
    
    public static final String DIVIDER = "____________________________________________________________\n";
    
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        ExpenseList expenseList = new ExpenseList();
        greet();
        runCommandLoopUntilExitCommand(expenseList);
        exit();
    }
    
    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today?");
        System.out.println(DIVIDER);
    }
    
    private static void runCommandLoopUntilExitCommand(ExpenseList expenseList) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        
        // continuously reads input from command line until command 'bye' is inputted
        while (!line.equalsIgnoreCase("bye")) {
            System.out.println(DIVIDER);
            runCommand(line);
            System.out.println(DIVIDER);
            line = in.nextLine();
        }
        in.close();
    }
    
    private static void runCommand(String line) {
        String command = line;
        try {
            Parser.parseCommand(command);
        } catch (Exception e) {
            System.out.println("Command not recognized");
        }
    }
    
    private static void exit() {
        System.out.println("Bye!");
    }
    
}
