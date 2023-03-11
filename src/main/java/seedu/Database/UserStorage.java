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
    private User user;
    private static final String csvDelimiter = ",";

    public UserStorage(String filePath) {
        super(filePath);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void write() throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filePath));
        String[] header = { "Name", "Weight", "Height" };
        writer.writeNext(header);
        writer.writeNext(user.toWriteFormat());
        writer.close();
    }

    @Override
    public void load() throws IOException {
        String splitBy = ",";
        String name;
        float weight, height;
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String[] userLine = br.readLine().split(csvDelimiter);
            name = userLine[0];
            weight = Float.parseFloat(userLine[1]);
            height = Float.parseFloat(userLine[2]);
            user = new User(name, weight, height);
            br.close();
        } catch (FileNotFoundException e) {
            File newFile = new File(filePath);
            newFile.createNewFile();
            user = new User();
        }
    }

    public void updateUser(String name, float weight, float height) {
        user.setName(name);
        user.setWeight(weight);
        user.setHeight(height);
        try {
            this.write();
            System.out.println("Successfully updated user settings!");
        } catch (IOException e) {
            System.out.println("Error updating user settings!");
        }
    }
}
