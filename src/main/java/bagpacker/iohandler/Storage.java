package bagpacker.iohandler;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Storage class contains methods to save and retrieve saved packing list in txt file
 */
public class Storage {

    public static ArrayList<Item> packingList = new ArrayList<>();
    private static final String FILE_PATH = "packingList.txt";

    //    to be edited by @coregano
    //    public static void load() throws FileNotFoundException {
    //        Scanner reader = new Scanner(new File(FILE_PATH));
    //        String line;
    //        while (reader.hasNext()) {
    //            line = reader.nextLine();
    //            boolean isPacked = line.charAt(1) == 'X';
    //            String itemDesc = line.substring(4);
    //            Item item = new Item(itemDesc, isPacked);
    //            PackingList.getItemList().add(item);
    //        }
    //    }

    public static void save() {
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        ArrayList<Item> packingList = PackingList.getItemList();
        for (int i = 0; i < packingList.size(); i++) {
            fw.write(packingList.get(i).toString() + "\n");
        }
    }
}
