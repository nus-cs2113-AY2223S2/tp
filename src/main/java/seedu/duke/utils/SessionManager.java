package seedu.duke.utils;

import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public static SessionManager getInstance(Inventory inventory) {
        if (sessionManager == null) {
            sessionManager = new SessionManager();
        }
        return sessionManager;
    }

    public static Inventory getSession() {
        return readCSV("./data/sample.csv");
    }
}
