package seedu.duke.storage;

import java.io.File;
import java.io.IOException;

public abstract class TextDataUtility {

    public void checkForListData(File listData) {
        if (!listData.exists()) {
            System.out.println("Data file does not exist, creating a new one");
            try {
                if (listData.createNewFile()) {
                    System.out.println("Created a new listData file");
                }
            } catch (IOException e) {
                System.out.println("We can't create a file for some reason :<, exiting program");
                System.exit(1);
            }
        }
    }
}
