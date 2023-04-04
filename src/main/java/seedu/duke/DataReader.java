package seedu.duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class DataReader {
    private static final String MODULES_FILE_PATH = "modules.txt";
    private static final String UNIVERSITIES_FILE_PATH = "universities.txt";
    private ArrayList<University> universities;
    private ArrayList<Module> puModules;

    public DataReader() {
        this.universities = new ArrayList<>();
        this.puModules = new ArrayList<>();
        readUnivData();
        readModData(MODULES_FILE_PATH, puModules);
    }

    private void readUnivData() {
        ClassLoader classLoader = DataReader.class.getClassLoader();
        InputStream input = classLoader.getResourceAsStream(UNIVERSITIES_FILE_PATH);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
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

    private void readModData(String modulesFilePath, ArrayList<Module> modules) {
        ClassLoader classLoader = DataReader.class.getClassLoader();
        InputStream input = classLoader.getResourceAsStream(modulesFilePath);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                if (row[3].equals("N/A")) {
                    row[3] = "0";
                }
                Module module = new Module(Integer.parseInt(row[0]), row[1], row[2],
                        Integer.parseInt(row[3]), row[4], row[5], Integer.parseInt(row[6]));
                modules.add(module);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        modules.sort(Comparator.comparing(Module::getPrintingLength));
    }

    public ArrayList<University> getUniversities() {
        return universities;
    }

    public ArrayList<Module> getModules() {
        return puModules;
    }

    public ArrayList<Module> getModules(int univId) {
        ArrayList<Module> modules = new ArrayList<>();
        for (Module module : this.puModules) {
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
