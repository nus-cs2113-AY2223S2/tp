package seedu.duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {
    private final String MODULES_FILE_PATH = "./data/modules.csv";
    private final String UNIVERSITIES_FILE_PATH = "./data/universities.csv";
    private ArrayList<University> universities;
    private ArrayList<Module> modules;

    public DataReader() {
        this.universities = new ArrayList<>();
        this.modules = new ArrayList<>();
        readUnivData();
        readModData();
    }

    private void readUnivData() {
        try (BufferedReader br = new BufferedReader(new FileReader(UNIVERSITIES_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                University university = new University(Integer.parseInt(row[0]), row[1], row[2]);
                universities.add(university);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readModData() {
        try (BufferedReader br = new BufferedReader(new FileReader(MODULES_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                if (row[3] == "N/A") {
                    row[3] = "0";
                }
                Module module = new Module(Integer.parseInt(row[0]), row[1], row[2],
                        Integer.parseInt(row[3]), row[4], row[5], Integer.parseInt(row[6]));
                modules.add(module);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<University> getUniversities() {
        return universities;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public ArrayList<Module> getModules(int univId) {
        ArrayList<Module> modules = new ArrayList<>();
        for (Module module : this.modules) {
            if (module.getUnivId() == univId) {
                modules.add(module);
            }
        }
        return modules;
    }

    public void main(String[] args) {
        DataReader dr = new DataReader();
        System.out.println(dr.getUniversities());
        System.out.println(dr.getModules());
    }
}
