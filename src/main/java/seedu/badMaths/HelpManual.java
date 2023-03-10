package seedu.badMaths;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HelpManual {
    protected static String filePath = "docs/HelpManual.txt";
    public static void readHelpManual() {
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(lineTxt);
                }
                read.close();
            } else {
                System.out.println("Cannot find Help Manual. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error while loading files. Please try again.");
            e.printStackTrace();
        }
    }

}
