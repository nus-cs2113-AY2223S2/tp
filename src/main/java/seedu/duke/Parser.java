package seedu.duke;
import seedu.duke.data.Expense;
import seedu.duke.data.ExpenseList;
import seedu.duke.data.Income;
import seedu.duke.data.IncomeList;
import seedu.duke.data.RecordList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Parser {
    
    public static final String FIELD_DEMARCATION = " /";

    public static void parseCommand(String line) {
        List<String> lineParts = splitLine(line);
        String command = lineParts.get(0);
        List<String> arguments = lineParts.subList(1, lineParts.size());
        HashMap<String, String> argumentsByField = sortArguments(arguments);
        switch(command) {
        case "add income":
            addIncomes(argumentsByField);
            break;
        case "add expense":
            addExpenses(argumentsByField);
            break;
        case "list income":
            IncomeList.printIncomeList();
            break;
        case "list expense":
            ExpenseList.printExpenseList();
            break;
        case "list":
            IncomeList.printIncomeList();
            ExpenseList.printExpenseList();
            break;
        default:
            System.out.println("Command not recognized, please enter a valid command!");
        }
    }
    
    public static ArrayList<String> splitLine(String line) {
        ArrayList<String> lineParts = new ArrayList<String>();
        lineParts.addAll(Arrays.asList(line.split(FIELD_DEMARCATION)));
        return lineParts;
    }
    
    public static HashMap<String, String> sortArguments(List<String> arguments) {
        HashMap<String, String> argumentsByField = new HashMap<String, String>();
        int argumentsCount = arguments.size();
        
        // split each argument according to their field and their value, and add into hashmap accordingly
        // Hashmap's key is its field, value is the value of the field
        for (int i = 0; i < argumentsCount; i++) {
            String argument = arguments.get(i);
            String[] fieldAndValue = argument.split(" ", 2);
            try {
                String field = fieldAndValue[0].trim();
                String value = fieldAndValue[1].trim();
                argumentsByField.put(field, value);
            } catch (NullPointerException e) {
                System.out.println("Error: arguments not inputted correctly/missing a value");
            }
        }
        return argumentsByField;
    }
    
    public static void addExpenses(HashMap<String, String> argumentsByField) {
        try {
            String expenseCategory = argumentsByField.get("c");
            String expenseDescription = argumentsByField.get("de");
            String expenseDate = argumentsByField.get("da");
            float expenseValue = Float.parseFloat(argumentsByField.get("v"));
            
            Expense exp = new Expense(expenseCategory, expenseDescription, expenseDate, expenseValue);
            ExpenseList.addExpense(exp);
            RecordList.addRecord(exp);
            System.out.println("Expense added");
        } catch (Exception e) {
            System.out.println("Trouble adding expenses");
        }
    }

    public static void addIncomes(HashMap<String, String> argumentsByField) {
        try {
            String incomeCategory = argumentsByField.get("c");
            String incomeDescription = argumentsByField.get("de");
            String incomeDate = argumentsByField.get("da");
            float incomeValue = Float.parseFloat(argumentsByField.get("v"));

            Income inc = new Income(incomeCategory, incomeDescription, incomeDate, incomeValue);
            IncomeList.addIncome(inc);
            RecordList.addRecord(inc);
            System.out.println("Income added");
        } catch (Exception e) {
            System.out.println("Trouble adding income");
        }
    }
}
