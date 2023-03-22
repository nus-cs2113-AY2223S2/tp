package chching;

import chching.record.Record;
import chching.record.Income;
import chching.record.IncomeList;
import chching.record.Expense;
import chching.record.ExpenseList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Models a class to handle storage for the program.
 */
public class Storage {
    private final File file;

    /**
     * Build constructor for the Storage class.
     * @param filepath the filepath of the storage.
     */
    Storage(String filepath) {
        String dirname = filepath.substring(0, filepath.lastIndexOf("/"));
        File dir = new File(dirname);
        dir.mkdirs();
        this.file = new File(filepath);
    }

    public ArrayList<Income> loadIncomes() {
        ArrayList<Income> incomes = new ArrayList<>();

        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] extract = line.split("\\|");
                String symbol = extract[0].trim();
                String description = extract[1].trim();
                String date = extract[2].trim();
                String value = extract[3].trim();

                if (symbol.equals("I")) {
                    Income income = new Income(description, date, Double.parseDouble(value));
                    incomes.add(income);
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Unfortunately, file can't be found. I'll make a new one!");
        }

        return incomes;
    }

    public ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();

        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] extract = line.split("\\|");
                String symbol = extract[0].trim();

                if (symbol.equals("E")) {
                    String category = extract[1].trim();
                    String description = extract[2].trim();
                    String date = extract[3].trim();
                    String value = extract[4].trim();

                    Expense expense = new Expense(category, description, date, Double.parseDouble(value));
                    expenses.add(expense);
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Unfortunately, file can't be found. I'll make a new one!");
        }

        return expenses;
    }

    public void save(IncomeList incomes, ExpenseList expenses) {
        try {
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);

            for (int i = 0; i < incomes.size(); i++) {
                Record income = incomes.get(i);
                String line = String.format("I | %s | %s | %.2f\n", income.getDescription(),
                        income.getDate(), income.getValue());
                fileWriter.write(line);
            }

            for (int i = 0; i < expenses.size(); i++) {
                Record expense = expenses.get(i);
                String line = String.format("E | %s | %s | %s | %.2f\n", expense.getCategory(),
                        expense.getDescription(), expense.getDate(), expense.getValue());
                fileWriter.write(line);
            }

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

