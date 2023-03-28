package seedu.brokeMan.save;

import java.io.*;
import java.time.Month;
import java.util.HashMap;
import static seedu.brokeMan.budget.Budget.budgetEachMonth;


public class SaveBudget {
    public static void writeFile(HashMap<Integer, HashMap<Month, Double>> budget) {
        try {
            FileWriter myWriter = new FileWriter("./data/BudgetData.txt");
            myWriter.flush();
            final String[] message = {""};
            budget.forEach((key, value) -> {
                message[0] += key + "_";
                value.forEach((month, val) -> {
                    message[0] += month + ":" + val + ",";
                });
                message[0] += "\n";
            });
            myWriter.write(message[0]);
            myWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static HashMap<Integer, HashMap<Month, Double>> readFile() {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            File file = new File("./data/BudgetData.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("_");
                int key = Integer.parseInt(parts[0]);
                HashMap<Month, Double> value = new HashMap<>();
                String[] entries = parts[1].split(",");
                for (String entry : entries) {
                    String[] pair = entry.split(":");
                    Month month = Month.valueOf(pair[0]);
                    double val = Double.parseDouble(pair[1]);
                    value.put(month, val);
                }
                budgetEachMonth.put(key, value);
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return budgetEachMonth;
    }
}
