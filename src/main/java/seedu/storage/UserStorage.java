package seedu.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;
import seedu.entities.User;

public class UserStorage extends Storage implements FileReadable, FileWritable {
    private static final String csvDelimiter = ",";
    private User user;

    public UserStorage(String filePath) {
        super(filePath);
        try {
            this.load();
            System.out.println("Initialised User Storage");
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
        String[] header = { "Name", "Weight", "Height", "Age", "Gender", "TargetWeight" };
        writer.writeNext(header);
        writer.writeNext(user.toWriteFormat());
        writer.close();
    }

    @Override
    public void load() throws IOException {
        String name;
        float weight;
        float height;
        int age;
        String gender;
        float targetWeight;

        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            // Skip Line 1 (header)
            br.readLine();

            String[] userLine = br.readLine().split(csvDelimiter);
            name = userLine[0];
            weight = Float.parseFloat(userLine[1]);
            height = Float.parseFloat(userLine[2]);
            age = Integer.parseInt(userLine[3]);
            gender = userLine[4];
            targetWeight = Float.parseFloat(userLine[5]);
            user = new User(name, weight, height, age, gender, targetWeight);
            br.close();
        } catch (FileNotFoundException e) {
            File newFile = new File(filePath);
            newFile.createNewFile();
            user = new User();
        } catch (NullPointerException e) {
            user = new User();
        } catch (ArrayIndexOutOfBoundsException e) {
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
