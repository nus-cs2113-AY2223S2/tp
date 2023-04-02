package chching;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import chching.record.Record;
import chching.record.Income;
import chching.record.IncomeList;
import chching.record.Expense;
import chching.record.ExpenseList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Models a class to handle storage for the program.
 */
public class Storage {
    private final File file;

    /**
     * Build constructor for the Storage class.
     * @param filepath the filepath of the storage.
     */
    Storage(String filepath){
        String dirname = filepath.substring(0, filepath.lastIndexOf("/"));
        File dir = new File(dirname);
        dir.mkdirs();
        this.file = new File(filepath);
    }

    public ArrayList<Income> loadIncomes() {
        // create a JSON parser object
        JSONParser parser = new JSONParser();

        ArrayList<Income> incomes = new ArrayList<>();

        try {
            // read the JSON file into a JSONObject
            Object obj = parser.parse(new FileReader(file));
            JSONArray entries = (JSONArray) obj;

            // loop through the entries and print each one
            for (Object entry : entries) {
                JSONObject jsonObj = (JSONObject) entry;
                String type = (String) jsonObj.get("type");
                String description = (String) jsonObj.get("description");
                String date = (String) jsonObj.get("date");
                Double amount = (Double) jsonObj.get("amount");

                if (type.equals("I")) {
                    Income income = new Income(description, LocalDate.parse(date), amount);
                    incomes.add(income);

                }
            }

        } catch (Exception e) {
            System.out.println("Unfortunately, income list file can't be found. I'll make a new one!");
        }

        return incomes;
    }

    public ArrayList<Expense> loadExpenses() {
        // create a JSON parser object
        JSONParser parser = new JSONParser();

        ArrayList<Expense> expenses = new ArrayList<>();

        try {
            // read the JSON file into a JSONObject
            Object obj = parser.parse(new FileReader(file));
            JSONArray entries = (JSONArray) obj;

            // loop through the entries and print each one
            for (Object entry : entries) {
                JSONObject jsonObj = (JSONObject) entry;
                String type = (String) jsonObj.get("type");
                String description = (String) jsonObj.get("description");
                String date = (String) jsonObj.get("date");
                Double amount = (Double) jsonObj.get("amount");

                if (type.equals("E")) {
                    String category = (String) jsonObj.get("category");
                    Expense expense = new Expense(description, category, LocalDate.parse(date), amount);
                    expenses.add(expense);

                }
            }

        } catch (Exception e) {
            System.out.println("Unfortunately, expense list file can't be found. I'll make a new one!");
        }

        return expenses;
    }

    public void save(IncomeList incomes, ExpenseList expenses) {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < incomes.size(); i++) {
            Record income = incomes.get(i);
            JSONObject obj = new JSONObject();
            obj.put("type", "I");
            obj.put("description", income.getDescription());
            obj.put("date", income.getDate().toString());
            obj.put("amount", income.getValue());

            jsonArray.add(obj);
        }

        for (int i = 0; i < expenses.size(); i++) {
            Record expense = expenses.get(i);
            JSONObject obj = new JSONObject();
            obj.put("type", "E");
            obj.put("category", expense.getCategory());
            obj.put("description", expense.getDescription());
            obj.put("date", expense.getDate().toString());
            obj.put("amount", expense.getValue());

            jsonArray.add(obj);
        }

        String jsonString = JSONValue.toJSONString(jsonArray);

        // write the JSONObject to a file
        try (FileWriter file = new FileWriter(this.file)) {
            file.write(jsonString);

        } catch (IOException e) {
            System.out.println("An error occurred while writing JSON data to file.");
            e.printStackTrace();
        }

    }
}

