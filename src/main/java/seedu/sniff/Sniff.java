package seedu.sniff;

import archive.Archive;
import exception.SniffException;
import functionalities.commands.Command;
import functionalities.storage.Storage;
import functionalities.ui.Ui;
import functionalities.parser.Parser;
import functionalities.SniffTasks;

public class Sniff {
    private static SniffTasks tasks;
    private static Ui ui;

    public Sniff() {
        ui = new Ui();
        tasks = new SniffTasks();
    }

    public void run() throws SniffException {
        String absolutePath = getPath();
        String archivePath = getArchivePath();
        try {
            Storage.openFile(absolutePath);
            Archive.openArchiveFile(archivePath);
        } catch (SniffException file) {
            ui.showErrorMessage();
        }
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readUserCommand();
                Ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.executeCommand(tasks);
                isExit = c.isExit();
            } catch (SniffException e) {
                ui.showErrorMessage();
            } finally {
                Storage.saveAppointments(absolutePath);
                Archive.saveArchivedAppointments(archivePath);
                Ui.showLine();
            }
        }
    }

    private static String getPath() {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "SniffAppointment.txt");
        return path.toString();
    }

    private static String getArchivePath() {
        String home = System.getProperty("user.home");
        java.nio.file.Path filepath = java.nio.file.Paths.get(home, "SniffTest.txt");
        return filepath.toString();
    }

    /**
     * Main entry-point for the java.sniff.Sniff application.
     */
    public static void main(String[] args) throws SniffException {
        new Sniff().run();
    }
}
