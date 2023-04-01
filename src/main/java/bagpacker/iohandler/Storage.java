package bagpacker.iohandler;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

//import java.io.File;
//import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
//import java.util.Scanner;

/**
 * Storage class contains methods to save and retrieve saved packing list in txt file
 */
public class Storage {

    private static final String FILE_PATH = "packing_list.txt";

    /**
     * Loads packingList with items containing relevant packedQuantity, totalQuantity and item description
     * @throws FileNotFoundException
     */
    public static void load() throws FileNotFoundException {
        Scanner reader = new Scanner(new File(FILE_PATH));
        String line;
        while (reader.hasNext()) {
            line = reader.nextLine();

            // packedQuantity of an item is integer form of number after first [
            int packedQuantity = Integer.parseInt(String.valueOf(line.charAt(line.indexOf('[') + 1)));

            // totalQuantity of an item is integer form of number after first /
            int totalQuantity = Integer.parseInt(String.valueOf(line.charAt(line.indexOf('/') + 1)));

            // itemDesc will be after a whitespace after first ]
            String itemDesc = line.substring(line.indexOf(']') + 2);
            Item item = new Item(totalQuantity, packedQuantity, itemDesc);
            PackingList.getItemList().add(item);
        }
    }

    /**
     * saves packingList onto user's file directory
     * @param packingList
     */
    public static void save(PackingList packingList) {
        try {
            writeToFile(packingList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void writeToFile(PackingList packingList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < packingList.size(); i++) {
            fw.write(PackingList.get(i).toString() + "\n");
        }
        fw.close();
    }
}
