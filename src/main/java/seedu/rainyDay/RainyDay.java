package seedu.rainyDay;

import seedu.rainyDay.command.command;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RainyDay {

    public static String filePath = "rainyDay.txt";

    public static FinancialReport financialReport = new FinancialReport(new ArrayList<>());


    public static void main(String[] args) {
        try {
            financialReport = new FinancialReport(loadFromFile(filePath));
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
                if (userInput.equalsIgnoreCase("exit")) {
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
        if (action.equalsIgnoreCase("add")) {
            String[] tokens = userInput.split("-", 2);
            String[] inputs = tokens[1].split("\\s+", 2);
            String flowDirection = inputs[0];
            String[] data = inputs[1].split("\\$");
            String description = data[0].trim();
            String amount = data[1];
            command.addFinancialStatement(description, flowDirection, Integer.parseInt(amount));
        } else if (action.equalsIgnoreCase("delete")) {
            String[] tokens = userInput.split("\\s+");
            int index = Integer.parseInt(tokens[1]);
            command.deleteFinancialStatement(index);
        } else if (action.equalsIgnoreCase("view")) {
            command.generateReport(financialReport);
        } else if (action.equalsIgnoreCase("help")) {
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
                storeStatements.add(statements.getFinancialStatement(i));
            }

            writeStream.writeObject(storeStatements);
            writeStream.flush();
            writeStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<FinancialStatement> loadFromFile(String filePath)
            throws IOException, ClassNotFoundException {
        FileInputStream readData = new FileInputStream(filePath);
        ObjectInputStream readStream = new ObjectInputStream(readData);
        ArrayList<FinancialStatement> statements = (ArrayList<FinancialStatement>) readStream.readObject();
        readStream.close();
        readData.close();

        return statements;
    }


}
