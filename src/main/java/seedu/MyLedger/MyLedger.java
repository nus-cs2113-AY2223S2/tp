package seedu.MyLedger;

import seedu.Parser.MainInputParser;
import seedu.TxtData.TxtFileStatus;
import seedu.Expenditure.ExpenditureList;
import seedu.commands.Command;
import seedu.commands.CommandResult;
import seedu.ui.Ui;

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
        Ui.greetUser();
        readUserInputs();
    }

    public static void readUserInputs() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!hasProcessedAllInputs(line, expenditures)) {
            line = in.nextLine();
        }
        in.close();
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
