package seedu.duke.achievements;

import org.w3c.dom.Text;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.storage.TextDataUtility;
import seedu.duke.ui.ErrorMessages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AchievementListHandler extends TextDataUtility {
    private static final String ACHIEVEMENT_LIST_FILE_LOCATION = "allAchievements.txt";
    private static ArrayList<Achievement> achievementList = new ArrayList<>();

    public void loadListFromMessage(ArrayList<Achievement> achievementList) {
        File AchievementTextFile = new File(ACHIEVEMENT_LIST_FILE_LOCATION);
        super.checkForListData(AchievementTextFile);
        try {
            pushDataToAchievementList(achievementList, AchievementTextFile);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }
    }

    private void pushDataToAchievementList(ArrayList<Achievement> achievementListList, File achievementListData) throws DukeError {
        try {
            Scanner listDataScanner = new Scanner(achievementListData);
            String currentLine = "";
            currentLine = listDataScanner.nextLine();

            int numAchivements = Integer.parseInt(currentLine);
            for (int i = 0; i < numAchivements; i++) {
                try {
                    String thisName = listDataScanner.nextLine();
                    String thisRequirement = listDataScanner.nextLine();
                    String thisCompleted = listDataScanner.nextLine();
                    String thisDifficulty = listDataScanner.next();
                    if ((thisName.equals("") || thisRequirement.equals("") || thisCompleted.equals("") || thisDifficulty.equals(""))) {
                        //File has ended
                        break;
                    }
                    Achievement newAchievement = new Achievement();
                    //N:
                    //R:
                    //C:
                    //D:

                } catch (DukeError e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            throw new DukeError(ErrorMessages.ERROR_FILE_READ.toString());
        } catch (NumberFormatException e) {
            throw new DukeError(ErrorMessages.ERROR_NUM_ACHIEVEMENT_NOT_FOUND.toString());
        }
    }

    private Achievement parse


}

