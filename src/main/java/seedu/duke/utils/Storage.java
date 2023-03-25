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
                if (fields.length != 7) {
                    Ui.printInvalidSessionFile();
                    return new Inventory();
                }
                Item item = new Item(fields[1], fields[2], Integer.parseInt(fields[3]),
                        Double.parseDouble(fields[4]), LocalDateTime.parse(fields[6]));
                if(inventory.getUpcCodes().containsKey(fields[2])){
                    if(inventory.getUpcCodes().get(fields[2]).compareTo(item)==-1){
                        inventory.getItemInventory().remove(inventory.getUpcCodes().get(fields[2]));
                        inventory.getItemInventory().add(item);
                        inventory.getUpcCodes().remove(fields[2]);
                        inventory.getUpcCodes().put(fields[2],item);
                    }
                }else{
                    inventory.getItemInventory().add(item);
                    inventory.getUpcCodes().put(fields[2], item);
                }
                String[] itemNames = item.getName().toLowerCase().split(" ");
                for (String itemName : itemNames) {
                    if (!inventory.getItemNameHash().containsKey(itemName)) {
                        inventory.getItemNameHash().put(itemName, new ArrayList<>());
                    }
                    inventory.getItemNameHash().get(itemName).add(item);
                    inventory.getTrie().add(itemName);
                }
                if(!inventory.getUpcCodesHistory().containsKey(fields[2])){
                    inventory.getUpcCodesHistory().put(fields[2],new ArrayList<>());
                }
                inventory.getUpcCodesHistory().get(fields[2]).add(item);
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
            int counter = 0;
            for (int i = 0; i < currentInventory.getItemInventory().size(); i++) {
                String itemUPC = currentInventory.getItemInventory().get(i).getUpc();
                for(Item item: currentInventory.getUpcCodesHistory().get(itemUPC)){
                    writer.write(counter + "," + item.getName() + "," + item.getUpc() + "," + item.getQuantity()
                            + "," + item.getPrice() + "," + item.getCategory() + "," + item.getDateTime()+"\n");
                    counter++;
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading");
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
