package seedu.duke.utils;

import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;

import java.io.*;
import java.util.ArrayList;

public class SessionManager {
    private static SessionManager sessionManager = null;

    private static Inventory inventory = new Inventory();

    private SessionManager() {
    }

    private static Inventory readCSV(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                if (fields.length != 6) {
                    Ui.invalidSessionFile();
                    return new Inventory();
                }
                Item item = new Item(fields[1], fields[2], fields[3], fields[4]);
                inventory.getItemInventory().add(item);
                inventory.getUpcCodes().put(fields[2], item);
                if (inventory.getItemNameHash().containsKey(fields[1])) {
                    inventory.getItemNameHash().get(fields[1]).add(item);
                } else {
                    ArrayList<Item> items = new ArrayList<>();
                    items.add(item);
                    inventory.getItemNameHash().put(fields[1], items);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            return new Inventory();
        }
        Ui.recoveredSessionFile();
        return inventory;
    }

    public void writeCSV(String fileName, Inventory currentInventory) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < currentInventory.getItemInventory().size(); i++) {
                Item item = currentInventory.getItemInventory().get(i);
                writer.write(i + "," + item.getName() + "," + item.getUpc() + "," + item.getQuantity() + "," +
                        item.getPrice() + "," + item.getCategory() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SessionManager getInstance(Inventory inventory) {
        if (sessionManager == null) {
            sessionManager = new SessionManager();
        }
        return sessionManager;
    }

    public static void writeSession(Inventory inventory) {
        sessionManager.writeCSV("./data/sample.csv", inventory);
    }

    public static Inventory getSession() {
        return readCSV("./data/sample.csv");
    }
}
