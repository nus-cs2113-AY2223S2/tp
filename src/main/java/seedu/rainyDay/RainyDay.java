package seedu.rainyDay;

import seedu.rainyDay.command.Command;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;


import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class RainyDay {

    public static String filePath = "rainyDay.txt";

    public static FinancialReport financialReport = new FinancialReport(new ArrayList<>());

    public static void main(String[] args) {

        try {
            financialReport = new FinancialReport(Storage.loadFromFile(filePath));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No valid save file detected. Starting with empty financial data.");
        }
        Scanner input = new Scanner(System.in);
        UI.printLogo();
        UI.greetUser(input.nextLine());

        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = input.nextLine().trim();
                if (userInput.equalsIgnoreCase(Command.COMMAND_EXIT)) {
                    isExit = true;
                }
                parseUserInput(userInput);
            } catch (Exception e) {
                System.out.println("Wrong input format! Please refer to help for correct user input!");
            }
        }
        UI.sayFarewellToUser();
    }

    public static void parseUserInput(String userInput) throws Exception {
        String action = userInput.split("\\s+")[0];
        if (action.equalsIgnoreCase(Command.COMMAND_ADD)) {
            String[] tokens = userInput.split("-", 2);
            String[] inputs = tokens[1].split("\\s+", 2);
            String flowDirection = inputs[0];
            String[] data = inputs[1].split("\\$");
            String description = data[0].trim();
            String amount = data[1];
            Command.addFinancialStatement(description, flowDirection, Integer.parseInt(amount));
        } else if (action.equalsIgnoreCase(Command.COMMAND_DELETE)) {
            String[] tokens = userInput.split("\\s+");
            int index = Integer.parseInt(tokens[1]);
            Command.deleteFinancialStatement(index);
        } else if (action.equalsIgnoreCase(Command.COMMAND_VIEW)) {
            Command.generateReport(financialReport);
        } else if (action.equalsIgnoreCase(Command.COMMAND_HELP)) {
            displayHelp();
        } else {
            unrecognisedInput();
        }
    }

    public static void clearFinancialReport() {
        financialReport.clearReport();
    }

    public static void displayHelp() {
        //thanks bao mi hua benjamin
        System.out.println("Have you tried reading the UG?");
    }

    public static void unrecognisedInput() {
        System.out.println("sorry! I do not understand your input!");
        System.out.println("Please refer to the help table!");
    }




}
