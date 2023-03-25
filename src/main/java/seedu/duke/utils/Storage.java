package seedu.duke.utils;

import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.types.Types;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.utils.ColorCode.*;

public class Storage {
    private static Inventory inventory = new Inventory();
    private static final String VALID_DATAROW_REGEX = "^\\d+,[^,]+,\\d+,\\d+,\\d+(?:\\.\\d+)?,[^,]+$";

    public Storage() {

    }

    public static Inventory readCSV() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(Types.SESSIONFILEPATH));
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(Types.SESSIONFILEPATH));
            for (int i = 0; i < currentInventory.getItemInventory().size(); i++) {
                Item item = currentInventory.getItemInventory().get(i);
                writer.write(i + "," + item.getName() + "," + item.getUpc() + "," + item.getQuantity() + "," +
                        item.getPrice() + "," + item.getCategory() + "\n");
            }
            writer.close();
        } catch (IOException e) {
        }
    }

    public static Types.FileHealth checkFileValid(String path) {
        File file = new File(path);

        if (!file.exists()) {
            return Types.FileHealth.MISSING;
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Pattern pattern = Pattern.compile(VALID_DATAROW_REGEX);
                Matcher matcher = pattern.matcher(line);

                if (!matcher.matches()) {
                    scanner.close();
                    return Types.FileHealth.CORRUPT;
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            return Types.FileHealth.MISSING;
        }

        return Types.FileHealth.OK;
    }

    public static String InventoryDataFileExist() {
        String path = Types.SESSIONFILEPATH;
        Types.FileHealth fileHealth = checkFileValid(path);

        switch (fileHealth) {
        case OK:
            return ANSI_GREEN + "VALID" + ANSI_RESET;
        case CORRUPT:
            return ANSI_RED + "CORRUPTED" + ANSI_RESET;
        case MISSING:
            return ANSI_ORANGE + "MISSING (Will be created if AutoSave is TRUE)" + ANSI_RESET;
        default:
            return "UNKNOWN";
        }
    }
}
