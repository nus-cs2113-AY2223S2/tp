package bagpacker.iohandler;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage class contains methods to save and retrieve saved packing list in txt file
 */
public class Storage {

    private static String filePath;

    public Storage(String filePATH) {
        filePath = filePATH;
    }

    /**
     * Loads packingList with items saved in save file
     * @throws FileNotFoundException when no save file in directory FILE_PATH is found
     */
    public static void load() throws FileNotFoundException {
        Scanner reader = new Scanner(new File(filePath));
        String line;
        while (reader.hasNext()) {
            line = reader.nextLine();
            PackingList.getItemList().add(readItem(line));
        }
    }

    /**
     * Returns an item with details saved in save file
     * @param line line from save file
     * @return item containing relevant packedQuantity, totalQuantity and item description
     */
    private static Item readItem(String line) {
        int openBracketIndex = line.indexOf('[');
        int forwardSlashIndex = line.indexOf('/');
        int closeBracketIndex = line.indexOf(']');

        // packedQuantity of an item is integer form of number after '[' and before '/'
        int packedQuantity = Integer.parseInt(line.substring(openBracketIndex + 1, forwardSlashIndex));

        // totalQuantity of an item is integer form of number after '/' and before ']'
        int totalQuantity = Integer.parseInt(line.substring(forwardSlashIndex + 1, closeBracketIndex));

        // itemDesc will be after '] '
        String itemDesc = line.substring(line.indexOf(']') + 2);
        return new Item(totalQuantity, packedQuantity, itemDesc);
    }

    /**
     * saves current list of items onto file directory FILE_PATH
     * @param packingList list of items used by user
     */
    public static void save(PackingList packingList) {
        try {
            writeToFile(packingList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void writeToFile(PackingList packingList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < packingList.size(); i++) {
            fw.write(PackingList.get(i).toString() + "\n");
        }
        fw.close();
    }
}
