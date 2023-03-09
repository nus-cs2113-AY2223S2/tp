package bagpacker.iohandler;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class contains methods to save and retrieve saved packing list in txt file
 */
public class Storage {
    public static ArrayList<Item> packingList = new ArrayList<>();
    private static final String FILE_PATH = "packingList.txt";
    public static ArrayList<Item> load() throws FileNotFoundException {
        Scanner reader = new Scanner(new File(FILE_PATH));
        String line;
        while (reader.hasNext()) {
            line = reader.nextLine();
            boolean isPacked = line.charAt(1) == 'X';
            String itemDesc = line.substring(4);
            Item item = new Item(itemDesc, isPacked);
            packingList.add(item);
        }
        return packingList;
    }

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
            fw.write(packingList.get(i).toString() + "\n");
        }
    }
}
