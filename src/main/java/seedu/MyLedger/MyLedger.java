package seedu.MyLedger;

import seedu.Parser.MainInputParser;
import seedu.TxtData.TxtFileStatus;
import seedu.Expenditure.ExpenditureList;
import seedu.commands.Command;
import seedu.commands.CommandResult;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyLedger {
    private static ExpenditureList expenditures;

    private static void start() {
        expenditures = new ExpenditureList();
    }

    public static void main(String[] args) {
        try {
            TxtFileStatus.fileAvailability();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        }
    }

    public static void runMyLedger() {
        start();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
        readUserInputs();
    }

    public static void readUserInputs() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while(!hasProcessedAllInputs(line, expenditures)) {
            line = in.nextLine();
        }
    }

    public static boolean hasProcessedAllInputs(String line, ExpenditureList expenditures) {
        // Parses the input
        Command finalCommand = MainInputParser.parseInputs(line);
        CommandResult result = finalCommand.execute(expenditures);
        String textOutput = result.getCommandResult();
        System.out.println(textOutput);
        return finalCommand.isExit();
    }
}
