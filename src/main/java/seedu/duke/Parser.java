package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    /**
     * Deletes the module corresponding to the uni specified by user. Module will the removed from user's
     * saved list of modules.
     *
     * @param moduleToDelete Module to be deleted from user's saved list of modules.
     * @param indexToDelete  Index of that module that is given in user input.
     * @param uniModuleList  The corresponding ArrayList of that specified uni.
     * @param database       Database of the user's saved list of modules.
     */
    public static void deleteModule(Module moduleToDelete, int indexToDelete, ArrayList<Module> uniModuleList,
                                    Storage database) {
        int indexToZeroBased = indexToDelete - 1;
        try {
            uniModuleList.remove(indexToDelete);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds");
        }

        try {
            database.writeListToFile(uniModuleList);
        } catch (IOException e) {
            System.out.println("Unable to save to database");
        }
    }

}
