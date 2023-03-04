package seedu.Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FoodStorage extends Storage implements FileReadable {
    public FoodStorage(String filePath) {
        super(filePath);
    }

    @Override
    public void read() {
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] food = line.split(splitBy); // use comma as separator
                for (int i = 0; i < food.length; i++) {
                    System.out.printf("%s ", food[i]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        // TODO Auto-generated method stub
        return;
    }

    public static void main(String[] args) {
        FoodStorage fs = new FoodStorage("data/FoodData.csv");
        try {
            fs.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
