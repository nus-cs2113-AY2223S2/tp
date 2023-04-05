package seedu.duke.utils;

import seedu.duke.objects.Alert;
import seedu.duke.objects.AlertList;
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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Storage {
    public static final int BEFORE_INDEX = -1;
    private static Inventory inventory = new Inventory();
    private static final Integer MAX_NUMBER_OF_FIELDS = 7;
    private static final Integer NAME_INDEX = 1;
    private static final Integer UPC_INDEX = 2;
    private static final Integer QUANTITY_INDEX = 3;
    private static final Integer PRICE_INDEX = 4;
    private static final Integer CAT_INDEX = 5;
    private static final Integer DATE_INDEX = 6;
    private static final Integer ALERT_FIELDS = 3;
    private static final Integer ALERT_UPC_INDEX = 0;
    private static final Integer ALERT_QTY_INDEX = 1;
    private static final Integer ALERT_MINMAX_INDEX = 2;

    private static final String VALID_DATAROW_REGEX =
            "^\\d+,[^,]+,\\d+,\\d+,\\d+(?:\\.\\d+)?,[^,]+,\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{9}$";
    private static final String VALID_ALERT_REGEX = "(.+),\\d+,(min|max)$";
    private static boolean isStorageWriteDone = true;
    private static boolean isRaceConditionDetected;

    /**
     * Reads the CSV file from Types.SESSIONFILEPATH and
     * returns an Inventory object.
     *
     * @return Inventory object
     */
    public static synchronized Inventory readCSV(String filePath) {
        inventory = new Inventory();
        isRaceConditionDetected = false;
        while (!isStorageWriteDone) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                isRaceConditionDetected = true;
                Ui.printRaceCondition();
                return new Inventory();
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            Types.FileHealth fileStatus = checkFileValid(filePath, VALID_DATAROW_REGEX);
            switch (fileStatus) {
            case MISSING:
                //fallthrough
            case EMPTY:
                Ui.printEmptySessionFile();
                return new Inventory();
            default:
                break;
            }
            String line = reader.readLine();
            if (line == null) {
                Ui.printEmptySessionFile();
                return new Inventory();
            }
            Inventory tempInventory = new Inventory();
            while (line != null) {
                String[] fields = line.trim().split(",");
                if (fields.length != MAX_NUMBER_OF_FIELDS) {
                    Ui.printInvalidSessionFile();
                    return new Inventory();
                }
                try {
                    LocalDateTime.parse(fields[DATE_INDEX]);
                    Integer.parseInt(fields[QUANTITY_INDEX]);
                    Double.parseDouble(fields[PRICE_INDEX]);
                } catch (DateTimeParseException | NumberFormatException e) {
                    Ui.printInvalidSessionFile();
                    return new Inventory();
                }
                Item item = parseItem(fields);
                updateInventory(tempInventory, item);
                updateHistory(inventory, item);
                line = reader.readLine();
            }
            updateInventory(tempInventory);
            reader.close();
        } catch (IOException | NumberFormatException e) {
            Ui.printEmptySessionFile();
            return new Inventory();
        }

        Ui.printRecoveredSessionFile();
        return inventory;
    }

    /**
     * Creates an Item object from the fields in the CSV file.
     *
     * @param fields String array of fields delimited by commas
     * @return Item object
     */
    private static Item parseItem(String[] fields) {
        return new Item(Sanitizer.decode(fields[NAME_INDEX]), fields[UPC_INDEX],
                Integer.parseInt(fields[QUANTITY_INDEX]),
                Double.parseDouble(fields[PRICE_INDEX]), fields[CAT_INDEX],
                LocalDateTime.parse(fields[DATE_INDEX]));
    }

    /**
     * Update the inventory with the item object provided. (Takes into account duplicate entries from history)
     *
     * @param inventory Inventory object to be updated
     * @param item      Item object to be added
     */

    private static void updateInventory(Inventory inventory, Item item) {
        if (inventory.getUpcCodes().containsKey(item.getUpc())) {
            Item existingItem = inventory.getUpcCodes().get(item.getUpc());
            if (existingItem.compareTo(item) == BEFORE_INDEX) {
                inventory.getItemInventory().remove(existingItem);
                inventory.getUpcCodes().remove(item.getUpc());
                inventory.getItemInventory().add(item);
                inventory.getUpcCodes().put(item.getUpc(), item);
            }
        } else {
            inventory.getItemInventory().add(item);
            inventory.getUpcCodes().put(item.getUpc(), item);
        }
    }

    /**
     * Updates the hashes.
     *
     * @param tempInventory Inventory object to be updated from
     */
    private static void updateInventory(Inventory tempInventory) {
        for (Item item : tempInventory.getItemInventory()) {
            Storage.inventory.getItemInventory().add(item);
            Storage.inventory.getUpcCodes().put(item.getUpc(), item);
            String[] itemNames = item.getName().toLowerCase().split(" ");
            for (String itemName : itemNames) {
                if (!Storage.inventory.getItemNameHash().containsKey(itemName)) {
                    Storage.inventory.getItemNameHash().put(itemName, new ArrayList<>());
                }
                Storage.inventory.getItemNameHash().get(itemName).add(item);
                Storage.inventory.getTrie().add(itemName);
            }
            String category = item.getCategory().toLowerCase();
            if (!Storage.inventory.getCategoryHash().containsKey(category)) {
                Storage.inventory.getCategoryHash().put(category, new ArrayList<>());
            }
            Storage.inventory.getCategoryHash().get(category).add(item);
        }
    }

    /**
     * Updates the history of the item.
     *
     * @param inventory Inventory object to be updated
     * @param item      Item object to be added
     */
    private static void updateHistory(Inventory inventory, Item item) {
        if (!inventory.getUpcCodesHistory().containsKey(item.getUpc())) {
            inventory.getUpcCodesHistory().put(item.getUpc(), new ArrayList<>());
        }
        inventory.getUpcCodesHistory().get(item.getUpc()).add(new Item(item));
    }


    /**
     * Writes the current inventory to the CSV file.
     *
     * @param currentInventory Current inventory
     */
    public static synchronized void writeCSV(final Inventory currentInventory) {
        isStorageWriteDone = false;
        try {
            File dataFolder = new File("./data");
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(Types.SESSIONFILEPATH));
            int counter = 0;
            for (int i = 0; i < currentInventory.getItemInventory().size(); i++) {
                String itemUPC = currentInventory.getItemInventory().get(i).getUpc();
                for (Item item : currentInventory.getUpcCodesHistory().get(itemUPC)) {
                    writer.write(counter + "," + Sanitizer.encode(item.getName()) + "," + item.getUpc()
                            + "," + item.getQuantity() + "," + item.getPrice() + "," + item.getCategory() + ","
                            + item.getDateTime() + "\n");
                    counter++;
                }
            }
            writer.close();
        } catch (IOException e) {
            isStorageWriteDone = true;
            System.out.println("Critical: An error occurred when writing to the file.");
        }
        isStorageWriteDone = true;
    }

    /**
     * Writes the current alert list to the AlertData CSV file.
     *
     * @param alertList Current alert list
     */
    public static synchronized void writeCSV(final AlertList alertList) {
        try {
            File dataFolder = new File("./data");
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(Types.ALERTFILEPATH));

            for (Map.Entry<String, Integer> entry : alertList.getMinAlertUpcs().entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                writer.write(key + "," + value + "," + "min" + "\n");
            }

            for (Map.Entry<String, Integer> entry : alertList.getMaxAlertUpcs().entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                writer.write(key + "," + value + "," + "max" + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Critical: An error occurred when writing to the file.");
        }

    }


    public static synchronized AlertList readAlertCSV() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Types.ALERTFILEPATH));
            String line = reader.readLine();
            if (line == null) {
                Ui.printEmptyAlertFile();
                return new AlertList();
            }

            AlertList tempAlertList = new AlertList(); //can set min and max hash maps in here
            while (line != null) {
                line = line.trim();
                String[] fields = line.split(",");
                if (fields.length != ALERT_FIELDS) {
                    Ui.printInvalidAlertFile();
                    return new AlertList();
                }
                String upc = fields[ALERT_UPC_INDEX];
                if (!inventory.getUpcCodes().containsKey(upc)) {
                    Ui.printInvalidAlertFile();
                    return new AlertList();
                }
                Alert alert = new Alert(fields[ALERT_UPC_INDEX], fields[ALERT_MINMAX_INDEX], fields[ALERT_QTY_INDEX]);

                if (fields[ALERT_MINMAX_INDEX].equals("min")) {
                    tempAlertList.setMinAlertUpcs(fields[ALERT_UPC_INDEX], Integer.parseInt(fields[ALERT_QTY_INDEX]));
                } else if (fields[ALERT_MINMAX_INDEX].equals("max")) {
                    tempAlertList.setMaxAlertUpcs(fields[ALERT_UPC_INDEX], Integer.parseInt(fields[ALERT_QTY_INDEX]));
                } else {
                    Ui.printInvalidAlertFile();
                    return new AlertList();
                }

                line = reader.readLine();
            }

            for (Map.Entry<String, Integer> entry : tempAlertList.getMinAlertUpcs().entrySet()) {
                inventory.getAlertList().setMinAlertUpcs(entry.getKey(), entry.getValue());
            }

            for (Map.Entry<String, Integer> entry : tempAlertList.getMaxAlertUpcs().entrySet()) {
                inventory.getAlertList().setMaxAlertUpcs(entry.getKey(), entry.getValue());
            }
            reader.close();
        } catch (IOException ioException) {
            Ui.printEmptyAlertFile();
            return new AlertList();
        } catch (NumberFormatException numberFormatException) {
            Ui.printInvalidAlertFile();
            return new AlertList();
        }
        Ui.printRecoveredAlertFile();
        return inventory.getAlertList();

    }

    /**
     * Checks if a given file path is valid.
     *
     * @param path File path
     * @return FileHealth enum that indicates the state of the file (MISSING/CORRUPT/OK)
     */
    public static synchronized Types.FileHealth checkFileValid(final String path, String validRow) {
        File file = new File(path);

        // Check if directory exists
        if (!file.exists()) {
            return Types.FileHealth.MISSING;
        }

        try {
            Scanner scanner = new Scanner(file);
            // Check if file is empty
            if (!scanner.hasNextLine()) {
                scanner.close();
                return Types.FileHealth.EMPTY;
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Pattern pattern = Pattern.compile(validRow);
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
    public static synchronized String checkDataFileExist(boolean isInventoryData) {
        Types.FileHealth fileHealth;
        if (isInventoryData) {
            fileHealth = checkFileValid(Types.SESSIONFILEPATH, VALID_DATAROW_REGEX);
        } else {
            fileHealth = checkFileValid(Types.ALERTFILEPATH, VALID_ALERT_REGEX);
        }

        switch (fileHealth) {
        case OK:
            return "VALID";
        case CORRUPT:
            return "CORRUPTED (Please delete the file or fix it manually)";
        case MISSING:
            return "MISSING (Will be created if AutoSave is TRUE)";
        case EMPTY:
            return "EMPTY";
        default:
            return "UNKNOWN";
        }
    }

}
