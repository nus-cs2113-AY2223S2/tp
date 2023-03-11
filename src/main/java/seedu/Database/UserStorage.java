package seedu.Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;
import seedu.Entities.User;

public class UserStorage extends Storage implements FileReadable, FileWritable {
    private static final String csvDelimiter = ",";
    private User user;

    public UserStorage(String filePath) {
        super(filePath);
        try {
            this.load();
        } catch (IOException e) {
            System.out.println("Error loading User Storage");
        }
    }

    @Override
    public void write() throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filePath),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.RFC4180_LINE_END);
        String[] header = { "Name", "Weight", "Height" };
        writer.writeNext(header);
        writer.writeNext(user.toWriteFormat());
        writer.close();
    }

    @Override
    public void load() throws IOException {
        String name;
        float weight;
        float height;
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            // Skip Line 1 (header)
            br.readLine();

            String[] userLine = br.readLine().split(csvDelimiter);
            name = userLine[0];
            weight = Float.parseFloat(userLine[1]);
            height = Float.parseFloat(userLine[2]);
            user = new User(name, weight, height);

            System.out.println("Loaded past user data.");
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("User File not found. Creating new user file...");
            File newFile = new File(filePath);
            newFile.createNewFile();
            user = new User();
        } catch (NullPointerException e) {
            System.out.println("User File empty. Initialising blank user...");
            user = new User();
        }
    }

    public void updateUser(User user) {
        this.user = user;
        try {
            this.write();
            System.out.println("Successfully updated user settings!");
        } catch (IOException e) {
            System.out.println("Error updating user settings!");
        }
    }

    public User getUser() {
        return this.user;
    }
}
