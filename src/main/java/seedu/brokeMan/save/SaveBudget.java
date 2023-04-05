package seedu.brokeMan.save;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
                if (parts.length != 2) {
                    continue;
                }
                int key;
                try {
                    key = Integer.parseInt(parts[0]);
                } catch (NumberFormatException e) {
                    continue;
                }
                HashMap<Month, Double> value = new HashMap<>();
                String[] entries = parts[1].split(",");
                for (String entry : entries) {
                    String[] pair = entry.split(":");
                    if (pair.length != 2) {
                        continue;
                    }
                    Month month;
                    try {
                        month = Month.valueOf(pair[0]);
                    } catch (IllegalArgumentException e) {
                        continue;
                    }
                    double val;
                    try {
                        val = Double.parseDouble(pair[1]);
                    } catch (NumberFormatException e) {
                        continue;
                    }
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
