package seedu.badMaths;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelpManual {
    protected static String filePath = "docs/HelpManual.txt";

    /**
     * This method reads the content from docs/HelpManual.txt file.
     */
    public static void readHelpManual() {
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                StringBuilder contentBuilder = new StringBuilder();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(lineTxt);
                    contentBuilder.append(lineTxt).append("\n");
                }
                read.close();
                // Add an assertion to check if the contents of the file match the expected contents
                String expectedContent = new String(Files.readAllBytes(Paths.get("docs/HelpManual.txt")));
                String actualContent = contentBuilder.toString();
                assert actualContent.equals(expectedContent) : "Contents of file do not match expected contents.";
            } else {
                System.out.println("Cannot find Help Manual. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error while loading files. Please try again.");
            e.printStackTrace();
        }
    }
}
