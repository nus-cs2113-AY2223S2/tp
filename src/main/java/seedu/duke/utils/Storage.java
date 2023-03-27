package seedu.duke.utils;

import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.types.Types;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.utils.ColorCode.ANSI_GREEN;
import static seedu.duke.utils.ColorCode.ANSI_ORANGE;
import static seedu.duke.utils.ColorCode.ANSI_RED;
import static seedu.duke.utils.ColorCode.ANSI_RESET;

public class Storage {
    private static Inventory inventory = new Inventory();
    private static final Integer MAX_NUMBER_OF_FIELDS = 7;
    private static final Integer NAME_INDEX = 1;
    private static final Integer UPC_INDEX = 2;
    private static final Integer QUANTITY_INDEX = 3;
    private static final Integer PRICE_INDEX = 4;
    private static final Integer DATE_INDEX = 6;

    private static final String VALID_DATAROW_REGEX =
            "^\\d+,[^,]+,\\d+,\\d+,\\d+(?:\\.\\d+)?,[^,]+,\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{9}$";

    /**
     * Reads the CSV file from Types.SESSIONFILEPATH and
     * returns an Inventory object.
     *
     * @return Inventory object
     */
    public static Inventory readCSV() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(Types.SESSIONFILEPATH));
            String line = reader.readLine();
            if (line == null) {
                Ui.printEmptySessionFile();
                return new Inventory();
            }
            Inventory tempInventory = new Inventory();
            while (line != null) {
                String[] fields = line.split(",");
                if (fields.length != MAX_NUMBER_OF_FIELDS) {
                    Ui.printInvalidSessionFile();
                    return new Inventory();
                }
                Item item = new Item(fields[NAME_INDEX], fields[UPC_INDEX], Integer.parseInt(fields[QUANTITY_INDEX]),
                        Double.parseDouble(fields[PRICE_INDEX]), LocalDateTime.parse(fields[DATE_INDEX]));
                if (tempInventory.getUpcCodes().containsKey(fields[UPC_INDEX])) {
                    if (tempInventory.getUpcCodes().get(fields[UPC_INDEX]).compareTo(item) == -1) {
                        tempInventory.getItemInventory().remove(tempInventory.getUpcCodes().get(fields[UPC_INDEX]));
                        tempInventory.getItemInventory().add(item);
                        tempInventory.getUpcCodes().remove(fields[UPC_INDEX]);
                        tempInventory.getUpcCodes().put(fields[UPC_INDEX], item);
                    }
                } else {
                    tempInventory.getItemInventory().add(item);
                    tempInventory.getUpcCodes().put(fields[UPC_INDEX], item);
                }
                if (!inventory.getUpcCodesHistory().containsKey(item.getUpc())) {
                    inventory.getUpcCodesHistory().put(item.getUpc(), new ArrayList<>());
                }
                inventory.getUpcCodesHistory().get(item.getUpc()).add(new Item(item));
                line = reader.readLine();
            }
            for (Item item : tempInventory.getItemInventory()) {
                inventory.getItemInventory().add(item);
                inventory.getUpcCodes().put(item.getUpc(), item);
                String[] itemNames = item.getName().toLowerCase().split(" ");
                for (String itemName : itemNames) {
                    if (!inventory.getItemNameHash().containsKey(itemName)) {
                        inventory.getItemNameHash().put(itemName, new ArrayList<>());
                    }
                    inventory.getItemNameHash().get(itemName).add(item);
                    inventory.getTrie().add(itemName);
                }
            }
            reader.close();
        } catch (IOException | NumberFormatException e) {
            Ui.printEmptySessionFile();
            return new Inventory();
        }
        Ui.printRecoveredSessionFile();
        return inventory;
    }

    /**
     * Writes the current inventory to the CSV file.
     *
     * @param currentInventory Current inventory
     */
    public static void writeCSV(final Inventory currentInventory) {
        try {
            File dataFolder = new File("./data");
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(Types.SESSIONFILEPATH));
            int counter = 0;
            for (int i = 0; i < currentInventory.getItemInventory().size(); i++) {
                String itemUPC = currentInventory.getItemInventory().get(i).getUpc();
                for (Item item : currentInventory.getItemInventory()) {
                    writer.write(counter + "," + item.getName() + "," + item.getUpc() + "," + item.getQuantity()
                            + "," + item.getPrice() + "," + item.getCategory() + "," + item.getDateTime() + "\n");
                    counter++;
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Critical: An error occurred when writing to the file.");
        }
    }

    /**
     * Checks if a given file path is valid.
     *
     * @param path File path
     * @return FileHealth enum that indicates the state of the file (MISSING/CORRUPT/OK)
     */
    public static Types.FileHealth checkFileValid(final String path) {
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

    /**
     * Checks if the data inside an inventory data .csv file is valid.
     *
     * @return String that will be printed which indicates the state of the file (MISSING/CORRUPT/OK/UNKNOWN)
     */
    public static String inventoryDataFileExist() {
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
