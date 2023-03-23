package seedu.duke.utils;

import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static String filePath;
    private static Inventory inventory = new Inventory();
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }
    public static Inventory readCSV() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            if (line == null) {
                Ui.printEmptySessionFile();
                return new Inventory();
            }
            while (line != null) {
                String[] fields = line.split(",");
                if (fields.length != 6) {
                    Ui.printInvalidSessionFile();
                    return new Inventory();
                }
                Item item = new Item(fields[1], fields[2],
                        Integer.parseInt(fields[3]), Double.parseDouble(fields[4]));
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
        } catch (IOException | NumberFormatException e) {
            Ui.printEmptySessionFile();
            return new Inventory();
        }
        Ui.printRecoveredSessionFile();
        return inventory;
    }

    public static void writeCSV(Inventory currentInventory) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
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
}
