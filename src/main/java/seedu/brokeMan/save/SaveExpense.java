package seedu.brokeMan.save;

import seedu.brokeMan.entry.Entry;
import seedu.brokeMan.entry.expense.Expense;
import seedu.brokeMan.entry.expense.ExpenseList;
import seedu.brokeMan.exception.BrokeManException;
import seedu.brokeMan.parser.Parser;
import seedu.brokeMan.parser.StringToCategory;
import seedu.brokeMan.parser.StringToTime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;

public class SaveExpense {
    /*
     * saves all expenses under any change
     */
    public static void writeFile(LinkedList<Entry> expenses) {
        try {
            FileWriter myWriter = new FileWriter("./data/ExpenseData.txt");
            myWriter.flush();
            String message;
            for (Entry expense : expenses) {
                message = "a/ " + expense.getAmount() +
                        " d/ " + expense.getInfo() +
                        " t/ " + expense.getTime().replaceAll("[T:-]", " ") +
                        " c/ " + expense.getCategory() +
                        "\n";
                myWriter.write(message);
            }
            myWriter.close();
        } catch (IOException foe) {
            try {
                Files.createDirectories(Path.of("./data"));
                File myObj = new File("./data/ExpenseData.txt");
                boolean newFile = myObj.createNewFile();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public static void readExpenseFile() {
        try {
            String filePath = "./data/ExpenseData.txt";
            ArrayList<String> expenseEntries;
            expenseEntries = (ArrayList<String>) Files.readAllLines(Paths.get(filePath),
                    StandardCharsets.UTF_8);
            for (String expenseEntry : expenseEntries) {
                String[] strExpense;
                try {
                    strExpense = Parser.checkAddCommandException(expenseEntry);

                    Expense expense = new Expense(Double.parseDouble(strExpense[0]),
                            strExpense[1],
                            StringToTime.convertStringToTime(strExpense[2]),
                            StringToCategory.convertStringToCategory(strExpense[3]));
                    ExpenseList.expenseList.add(expense);
                } catch (BrokeManException bme) {
                    continue;
                }
            }
        } catch (IOException ioe) {
            try {
                Files.createDirectories(Path.of("./data"));
                File myObj = new File("./data/ExpenseData.txt");
                boolean newFile = myObj.createNewFile();
            } catch (IOException fcIoe) {
                ioe.printStackTrace();
            }
        }
    }


}

