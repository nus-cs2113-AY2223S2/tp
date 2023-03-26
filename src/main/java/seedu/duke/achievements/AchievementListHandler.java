package seedu.duke.achievements;

//import org.w3c.dom.Text;
//import seedu.duke.Duke;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.storage.TextDataUtility;
import seedu.duke.ui.ErrorMessages;

import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AchievementListHandler extends TextDataUtility {
    private static final String ACHIEVEMENT_LIST_FILE_LOCATION = "allAchievements.txt";
    private static final int NUM_PARAMS_ACHIEVEMENT = 4;
    private static final int NAME_INDEX = 0;
    private static final int REQUIREMENT_INDEX = 1;
    private static final int COMPLETED_INDEX = 2;
    private static final int DIFFICULTY_INDEX = 3;
    private static final String COMPLETED = "1";
    private static final String NOT_COMPLETED = "0";
    private static final String EASY_PREFIX = "E";
    private static final String MEDIUM_PREFIX = "M";
    private static final String HARD_PREFIX = "H";
    private static final String SPLIT_CHAR = ":";
    private static ArrayList<Achievement> achievementList = new ArrayList<>();

    public void loadListFromMessage(ArrayList<Achievement> achievementList) {
        File achievementtextfile = new File(ACHIEVEMENT_LIST_FILE_LOCATION);
        super.checkForListData(achievementtextfile);
        try {
            pushDataToAchievementList(achievementList, achievementtextfile);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }
    }

    private void pushDataToAchievementList(ArrayList<Achievement> achievementList,
                                           File achievementListData) throws DukeError {
        try {
            Scanner listDataScanner = new Scanner(achievementListData);
            String currentLine = "";
            while (listDataScanner.hasNext()) {
                try {
                    currentLine = listDataScanner.nextLine();
                    Achievement newAchievement = parseAchivement(currentLine);
                    achievementList.add(newAchievement);

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

    private Achievement parseAchivement(String achievementInput) throws DukeError {
        String name;
        String requirement;
        boolean completed;
        AchievementDifficulty difficulty;

        String[] parsedString = achievementInput.split(SPLIT_CHAR);
        if (parsedString.length != NUM_PARAMS_ACHIEVEMENT) {
            throw new DukeError(ErrorMessages.ERROR_LOAD_CORRUPT_ACHIEVEMENT_DATA.toString());
        }
        name = parsedString[NAME_INDEX];
        requirement = parsedString[REQUIREMENT_INDEX];
        if (parsedString[COMPLETED_INDEX].equals(NOT_COMPLETED) || parsedString[COMPLETED_INDEX].equals(COMPLETED)) {
            int thisCompleted = Integer.parseInt(parsedString[COMPLETED_INDEX]);
            if (thisCompleted == 1) {
                completed = true;
            } else if (thisCompleted == 0) {
                completed = false;
            } else {
                throw new DukeError(ErrorMessages.ERROR_LOAD_CORRUPT_ACHIEVEMENT_DATA.toString());
            }
        } else {
            throw new DukeError(ErrorMessages.ERROR_LOAD_CORRUPT_ACHIEVEMENT_DATA.toString());
        }

        if (parsedString[DIFFICULTY_INDEX].equals(EASY_PREFIX)) {
            difficulty = AchievementDifficulty.EASY;
        } else if (parsedString[DIFFICULTY_INDEX].equals(MEDIUM_PREFIX)) {
            difficulty = AchievementDifficulty.MEDIUM;
        } else if (parsedString[DIFFICULTY_INDEX].equals(HARD_PREFIX)) {
            difficulty = AchievementDifficulty.HARD;
        } else {
            throw new DukeError(ErrorMessages.ERROR_LOAD_CORRUPT_ACHIEVEMENT_DATA.toString());
        }
        Achievement achievement = new Achievement(name, requirement, completed, difficulty);
        return achievement;

    }


}

