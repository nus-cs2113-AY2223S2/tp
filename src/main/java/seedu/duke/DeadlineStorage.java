package seedu.duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DeadlineStorage implements DatabaseInterface {

    private static final String SAVED_DEADLINES_FILE_PATH = "data/deadlines.txt";
    private ArrayList<Deadline> deadlines;

    public DeadlineStorage() {
        this.deadlines = new ArrayList<>();
        try {
            initialiseDatabase();
        } catch (IOException e) {
            System.out.println("Initialise Deadlines Failure");
        }
    }

    @Override
    public void initialiseDatabase() throws IOException {
        File savedDeadlinesFile = new File(SAVED_DEADLINES_FILE_PATH);
        if (!savedDeadlinesFile.exists()) {
            File directory = new File("data");
            directory.mkdirs();
            savedDeadlinesFile.createNewFile();
            return;
        }
        readDeadlineData(SAVED_DEADLINES_FILE_PATH, deadlines);
        boolean isCorrupted = checkDatabaseCorrupted();
        if (isCorrupted) {
            UI.printDeadlineStorageCorruptedMessage();
        }
        writeDeadlinesToFile(deadlines);
    }

    private void readDeadlineData(String deadlineFilePath, ArrayList<Deadline> deadlines) {
        try (BufferedReader br = new BufferedReader(new FileReader(deadlineFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split("//");
                if (row.length != 2 || row[0].isBlank()) {
                    System.out.println("One deadline is corrupted, will be removed");
                    return;
                }
                Deadline deadline = new Deadline(row[0], row[1]);
                deadlines.add(deadline);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the deadline specified by user. Deadline will the removed from user's
     * list of deadlines.
     *
     * @param indexToDelete    Index of that deadline that is given in user input.
     * @param deadlines        The ArrayList of deadlines.
     * @param deadlineDatabase Database of the user's list of deadlines.
     * @return True if successfully deleted the module, false if unsuccessful.
     */
    public static boolean deleteDeadline(int indexToDelete, ArrayList<Deadline> deadlines,
                                         DeadlineStorage deadlineDatabase) {
        int indexToZeroBased = indexToDelete - 1;
        try {
            deadlines.remove(indexToZeroBased);
        } catch (IndexOutOfBoundsException e) {
            UI.printDeleteNumError();
            return false;
        }
        try {
            deadlineDatabase.writeDeadlinesToFile(deadlines);
        } catch (IOException e) {
            UI.printWriteToDatabaseFailureMessage();
            return false;
        }
        return true;
    }

    /**
     * Adds and overwrites ArrayList of user's deadlines in database.
     *
     * @param deadlines ArrayList of deadlines to be written into database.
     * @throws IOException If input/output operations fail or are interrupted.
     */
    public void writeDeadlinesToFile(ArrayList<Deadline> deadlines) throws IOException {
        FileWriter fw = new FileWriter(SAVED_DEADLINES_FILE_PATH);
        String stringToAdd = "";
        for (Deadline deadline : deadlines) {
            stringToAdd += writeTaskPreparation(deadline.toString());
        }
        fw.write(stringToAdd);
        fw.close();
    }

    public ArrayList<Deadline> getDeadlines() {
        return deadlines;
    }

    public void addDeadlineToDeadlines(Deadline deadlineToAdd) {
        if (deadlineToAdd == null) {
            UI.printAddDeadlineFailureMessage();
            return;
        }
        deadlines.add(deadlineToAdd);
        try {
            saveDeadlineToStorage(deadlineToAdd.toString());
        } catch (IOException e) {
            UI.printAddDeadlineFailureMessage();
        }
    }

    private void saveDeadlineToStorage(String saveDeadlineString) throws IOException {
        FileWriter fw = new FileWriter(SAVED_DEADLINES_FILE_PATH, true);
        fw.write(writeTaskPreparation(saveDeadlineString));
        fw.close();
    }

    public void compareDeadlines(ArrayList<Deadline> deadlines) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String todayDate = formatter.format(date);
        int counter = 1;
        try {
            boolean hasReminderMsgPrinted = false;
            for (Deadline deadline : deadlines) {
                Date today = formatter.parse(todayDate);
                Date deadlineDue = formatter.parse(deadline.getDueDate());
                long timeDiff = Math.abs(deadlineDue.getTime() - today.getTime());
                long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
                if (daysDiff <= 7) {
                    if (!hasReminderMsgPrinted) {
                        UI.printReminderMessage();
                        hasReminderMsgPrinted = true;
                    }
                    UI.printReminderDeadline(deadline, counter);
                    counter++;
                }
            }
            if (hasReminderMsgPrinted) {
                UI.printLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkDatabaseCorrupted() {
        boolean isCorrupted = false;
        ArrayList<Deadline> copyDeadlines = new ArrayList<>();
        for (Deadline deadline : deadlines) {
            String dueDate = deadline.getDueDate().trim();
            String task = deadline.getTask().trim();
            String[] dueDateFormat = dueDate.split("-");
            if (dueDateFormat.length != 3) {
                isCorrupted = true;
                continue;
            }
            String dueDateDate = dueDateFormat[0];
            String dueDateMonth = dueDateFormat[1];
            String dueDateYear = dueDateFormat[2];
            boolean isValidDate = checkValidDate(dueDateDate, dueDateMonth, dueDateYear);
            if (!isValidDate) {
                continue;
            }
            Deadline deadlineTypeToAdd = new Deadline(task, dueDate);
            copyDeadlines.add(deadlineTypeToAdd);
        }
        deadlines.clear();
        for (Deadline copyDeadline : copyDeadlines) {
            deadlines.add(copyDeadline);
        }
        return isCorrupted;
    }

    private boolean checkValidDate(String dueDateDate, String dueDateMonth, String dueDateYear) {
        if (dueDateDate.length() != 2 || dueDateMonth.length() != 2 || dueDateYear.length() != 4) {
            return false;
        }
        int date;
        int month;
        int year;
        try {
            date = Integer.parseInt(dueDateDate);
            month = Integer.parseInt(dueDateMonth);
            year = Integer.parseInt(dueDateYear);
        } catch (NumberFormatException e) {
            return false;
        }
        try {
            LocalDate.of(year, month, date);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

}
