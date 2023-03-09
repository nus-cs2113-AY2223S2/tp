package seedu.rainyDay;

import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class rainyDay {

    public static String filePath = "rainyDay.txt";

    public static FinancialReport financialReport = new FinancialReport(loadFromFile(filePath));

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        UI.printLogo();
        UI.greetUser(input.nextLine());

        boolean isExit = false;
        while(!isExit) {
            String userInput = input.nextLine().trim();
            if(userInput.equalsIgnoreCase("exit")) {
                isExit = true;
            }
            parseUserInput(userInput);
        }

        UI.sayFarewellToUser();
    }

    public static void parseUserInput(String userInput) {
        String action = userInput.split("\\s+")[0];
        if (action.equalsIgnoreCase("add")) {
            String[] tokens = userInput.split("-", 2);
            String[] inputs = tokens[1].split("\\s+",2);
            String flowDirection = inputs[0];
            String[] data = inputs[1].split("\\$");
            String description = data[0].trim();
            String amount = data[1];
            addFinancialStatement(description, flowDirection, Integer.parseInt(amount));
        } else if (action.equalsIgnoreCase("delete")) {
            String[] tokens= userInput.split("\\s+");
            int index = Integer.parseInt(tokens[1]);
            deleteFinancialStatement(index);
        } else if (action.equalsIgnoreCase("view")) {
            generateReport(financialReport);
        } else if (action.equalsIgnoreCase("help")) {
            displayHelp();
        } else {
            unrecognisedInput();
        }
    }

    public static String addFinancialStatement(String description, String flowDirection, int value) {
        financialReport.addStatement(new FinancialStatement(description, flowDirection, value));
        String direction = financialReport.getStatementDirection(financialReport.getStatementCount() - 1);
        String addStatement = "Done, added: " + direction + " for " + description + ", $" + value;
        writeToFile(financialReport, filePath);
        return addStatement;
    }

    public static String deleteFinancialStatement(int index) {
        index -= 1;
        String deleteStatement = "Done, deleted \"" + financialReport.getStatementDescription(index)
                + "\" from the financial report";
        financialReport.deleteStatement(index);
        writeToFile(financialReport, filePath);
        return deleteStatement;
    }

    public static String generateReport(FinancialReport financialReport) {
        if (financialReport.getStatementCount() == 0) {
            return "Your financial report is empty";
        }
        int totalInflow = 0;
        int totalOutflow = 0;
        String financialStatements = "";
        for (int i = 0; i < financialReport.getStatementCount(); i += 1) {
            if (financialReport.getStatementDirection(i).equals("in")) {
                totalInflow += financialReport.getStatementValue(i);
            } else {
                totalOutflow += financialReport.getStatementValue(i);
            }
            int index = i + 1;
            String financialStatement = String.join("", String.valueOf(index), ". ",
                    financialReport.getFullStatement(i), System.lineSeparator());
            financialStatements = String.join("", financialStatements, financialStatement);
        }
        String inflowInformation = "Inflow: $" + totalInflow;
        String outflowInformation = "Outflow: $" + totalOutflow;
        String remainingValueInformation = "Remaining value: $" + (totalInflow - totalOutflow);
        String report = String.join(System.lineSeparator(), financialStatements, inflowInformation, outflowInformation,
                remainingValueInformation);
        return report;
    }

    public static void displayHelp() {
        //thanks bao mi hua benjamin
    }

    public static void unrecognisedInput() {
        System.out.println("sorry! I do not understand your input!");
        System.out.println("Please refer to the help table!");
    }

    public static void writeToFile(FinancialReport statements, String filePath) {
        try {
            FileOutputStream writeData = new FileOutputStream(filePath);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            ArrayList<FinancialStatement> storeStatements = new ArrayList<>();
            for (int i = 0; i < statements.getStatementCount(); i += 1) {
                storeStatements.add(financialReport.getFinancialStatement(i));
            }

            writeStream.writeObject(storeStatements);
            writeStream.flush();
            writeStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<FinancialStatement> loadFromFile(String filePath) {
        try {
            FileInputStream readData = new FileInputStream(filePath);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            ArrayList<FinancialStatement> statements = (ArrayList<FinancialStatement>) readStream.readObject();
            readStream.close();
            readData.close();

            return statements;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No valid save file detected. Starting with empty financial data.");
            ArrayList<FinancialStatement> statements = new ArrayList<>();
            return statements;
        }
    }
}
