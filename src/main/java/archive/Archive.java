package archive;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

import exception.SniffException;
import functionalities.SniffTasks;
import functionalities.parser.ArchiveParser;
import functionalities.ui.Ui;

/**
 * This class deals with Archive (Loading and Saving Archived Tasks in the File)
 **/
public class Archive {

    /**
     * Saves the Archived task contents into the SniffArchive File
     *
     * @param filePath The path of the SniffArchive file
     * @throws SniffException if Archive file is not found
     * */
    public static void saveArchivedAppointments(String filePath) throws SniffException {
        try {
            File archiveFile = new File(filePath);
            FileWriter archiveSavedFile = new FileWriter(archiveFile, false);
            SniffTasks.archivedTasks(archiveSavedFile);
            archiveSavedFile.write(System.getProperty("line.separator"));
            archiveSavedFile.close();
        } catch (IOException e) {
            throw new SniffException(" Archive file not found!");
        }
    }

    /**
     * Reads and adds the Archived task contents into the Appointments task list
     *
     * @param filePath The path of the SniffArchive file
     * @throws SniffException if Archive file is not found
     * */
    public static void openArchiveFile(String filePath) throws SniffException {
        try {
            File archiveFile = new File(filePath);
            if (archiveFile.createNewFile()) {
                Ui.printArchiveFileCreated(true);
            } else {
                extractArchiveData(archiveFile);
            }
        } catch (IOException e) {
            Ui.printArchiveFileCreated(false);
        } catch (IndexOutOfBoundsException e) {
            throw new SniffException(" Archive file content saved in incorrect format!");
        }
    }

    /**
     * Calls ArchiveParser to parse Archived task contents
     *
     * @param archiveFile The SniffArchive file
     * @throws SniffException if errors are encountered while parsing
     * @throws FileNotFoundException if Archive file is not found
     * */
    public static void extractArchiveData(File archiveFile) throws SniffException, FileNotFoundException {
        try {
            Scanner s = new Scanner(archiveFile);
            int lineNo = 0;
            while (s.hasNext()) {
                String content = s.nextLine();
                lineNo++;
                char type = content.charAt(0);
                if (type == 'C') {
                    ArchiveParser.addConsult(content);
                } else if (type == 'V') {
                    ArchiveParser.addVaccine(content);
                } else if (type == 'S') {
                    ArchiveParser.addSurgery(content);
                } else {
                    throw new SniffException(" Incorrect appointment format found in line " + lineNo +
                            " of the archive file!");
                }
            }
        } catch (FileNotFoundException e) {
            throw new SniffException(" Archive file not found!");
        }
    }
}
