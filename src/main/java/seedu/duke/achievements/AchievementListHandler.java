package seedu.duke.achievements;

//import org.w3c.dom.Text;
//import seedu.duke.Duke;
import seedu.duke.achievements.types.AchievementBodyPart;
import seedu.duke.achievements.types.AchievementGymStatic;
import seedu.duke.achievements.types.AchievementLevel;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.storage.TextDataUtility;
import seedu.duke.ui.ErrorMessages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//@@author: ChubbsBunns
public class AchievementListHandler extends TextDataUtility {
    private static final int NUM_PARAMS_ACHIEVEMENT = 7;
    private static final int NAME_INDEX = 0;
    private static final int REQUIREMENT_INDEX = 1;
    private static final int COMPLETED_INDEX = 2;
    private static final int DIFFICULTY_INDEX = 3;
    private static final int TYPE_INDEX = 4;
    private static final int COUNT_CURRENT_INDEX = 5;
    private static final int COUNT_TO_COMPLETE_INDEX = 6;
    private static final String COMPLETED = "1";
    private static final String NOT_COMPLETED = "0";
    private static final String EASY_PREFIX = "E";
    private static final String MEDIUM_PREFIX = "M";
    private static final String HARD_PREFIX = "H";
    private static final String SPLIT_CHAR = ":";
    private static final String BRACKET = ") ";
    private static final String BLANK = " ";
    private static final String EMPTY = "";
    private static ArrayList<Achievement> achievementList = new ArrayList<>();

    public void loadAchievementsFromFile() {
        File achievementTextFile = new File(ACHIEVEMENT_LIST_FILE_LOCATION);
        super.checkForListData(achievementTextFile);
        try {
            pushDataToAchievementList(achievementList, achievementTextFile);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }
    }

    public void clearAchievementsData() {
        File achievementTextFile = new File(ACHIEVEMENT_LIST_FILE_LOCATION);
        clearAchievementListData(achievementTextFile);
        achievementList.clear();
        loadAchievementsFromFile();
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

    public void printAchievements () {
        System.out.println("Here are all the achievements");
        for (int i = 0; i < achievementList.size(); i++) {
            System.out.print((i + 1) + BRACKET + BLANK);
            System.out.println(achievementList.get(i).toString());
        }
    }

    /**
     * This parses data from the text file and ensures that it is loaded correctly
     * Each achievement is saved line by line in the following format:
     * NAME:REQUIREMENT:COMPLETED_OR_NOT:DIFFICULTY_INDEX:TYPE:CURRENT_COUNT:COUNT_TO_COMPLETE
     * @param achievementInput This refers to each line of the saved text file
     * @return Returns an achievement to be stored by the system and whether
     *         it is completed or not
     * @throws DukeError Whenever a specific achievment is not saved correctly,
     *         it is deemed corrupt and will not be loaded in correctly.
     */
    private Achievement parseAchivement(String achievementInput) throws DukeError, NumberFormatException {
        String name;
        String requirement;
        boolean completed;
        AchievementDifficulty difficulty;
        String type;
        int countCurr;
        int countToComplete;

        String[] parsedString = achievementInput.split(SPLIT_CHAR);
        if (parsedString.length != NUM_PARAMS_ACHIEVEMENT) {
            throw new DukeError(ErrorMessages.ERROR_LOAD_CORRUPT_ACHIEVEMENT_DATA.toString());
        }
        name = parsedString[NAME_INDEX];
        requirement = parsedString[REQUIREMENT_INDEX];
        completed = getCompleteValue(parsedString[COMPLETED_INDEX]);
        difficulty = getDifficultyValue(parsedString[DIFFICULTY_INDEX]);
        type = parsedString[TYPE_INDEX];
        countCurr = Integer.parseInt(parsedString[COUNT_CURRENT_INDEX]);
        countToComplete = Integer.parseInt(parsedString[COUNT_TO_COMPLETE_INDEX]);
        Achievement achievement = createAchievement(name,
                requirement,
                completed,
                difficulty,
                type,
                countCurr,
                countToComplete);
        return achievement;
    }


    /**
     * Takes in a String value from the save file and evaluates whether it is valid (only 0 or 1 accepted)
     * If it is valid, then check whether the specific achievement is completed or not.
     * @param input Input from the save file that corresponds to whether an achievement is complete
     * @return Returns a boolean value indicating whether the achievement is complete or not
     * @throws DukeError Throws an error if the format is incorrect
     */
    private boolean getCompleteValue (String input) throws DukeError {
        boolean completed;
        if (input.equals(NOT_COMPLETED) || input.equals(COMPLETED)) {
            int thisCompleted = Integer.parseInt(input);
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
        return completed;
    }

    private AchievementDifficulty getDifficultyValue(String input) throws DukeError {
        AchievementDifficulty achievementDifficulty;
        if (input.equals(EASY_PREFIX)) {
            achievementDifficulty = AchievementDifficulty.EASY;
        } else if (input.equals(MEDIUM_PREFIX)) {
            achievementDifficulty = AchievementDifficulty.MEDIUM;
        } else if (input.equals(HARD_PREFIX)) {
            achievementDifficulty = AchievementDifficulty.HARD;
        } else {
            throw new DukeError(ErrorMessages.ERROR_LOAD_CORRUPT_ACHIEVEMENT_DATA.toString());
        }
        return achievementDifficulty;
    }

    /**
     * @param name Name of the achievement
     * @param requirement Requirements on how to achieve this achievement
     * @param completed Whether this achievement is completed or not
     * @param difficulty The difficulty level of the achievement, represented by stars
     * @param achievementType Type of achievement (by exercise category)
     * @param currCount Number of exercises from this exercise category this achievement is assigned to
     * @param totalCountToComplete Total number of exercises required to complete this achievement
     * @return Returns an achievement object for use by the main application
     * @throws DukeError
     */
    private Achievement createAchievement(String name, String requirement,
                                          boolean completed,
                                          AchievementDifficulty difficulty ,
                                          String achievementType, int currCount,
                                          int totalCountToComplete) throws DukeError{
        switch (achievementType.toLowerCase()) {
        case "easy":
        case "medium":
        case "hard":
            return new AchievementLevel(name, requirement, completed, difficulty,
                    achievementType.toLowerCase(), currCount, totalCountToComplete);
        case "upper":
            //upper has a different string than the raw command
            String achievementName = "upper body";
            return new AchievementBodyPart(name, requirement, completed, difficulty,
                    achievementName, currCount, totalCountToComplete);
        case "core":
        case "legs":
            return new AchievementBodyPart(name, requirement, completed, difficulty,
                    achievementType.toLowerCase(), currCount, totalCountToComplete);
        case "gym":
        case "static":
            return new AchievementGymStatic(name, requirement, completed, difficulty,
                    achievementType.toLowerCase(), currCount, totalCountToComplete);
        default:
            throw new DukeError(ErrorMessages.ERROR_LOAD_CORRUPT_ACHIEVEMENT_DATA.toString());
        }
    }

    public ArrayList<Achievement> getAchievementList() {
        return achievementList;
    }

    public void saveAchievementList() {
        try {
            clearFile();
        } catch (IOException e) {
            System.out.println(ErrorMessages.ERROR_DELETE_CONTENT_ERROR_TEXT);
        }

        for (int i = 0; i < achievementList.size(); i++) {
            String achievementSaveData = achievementList.get(i).parseDataForSaving();
            try {
                appendToFile(achievementSaveData);
            } catch (IOException e) {
                System.out.println(ErrorMessages.ERROR_UNABLE_TO_WRITE_TO_FILE);
            }
        }
    }


    /**
     * Deletes all data, used for clearing achievement data.
     * @throws IOException
     */
    private static void clearFile() throws IOException {
        FileWriter fw = new FileWriter(ACHIEVEMENT_LIST_FILE_LOCATION);
        fw.write(EMPTY);
        fw.close();
    }

    private static void appendToFile(String textToAppend) throws IOException {
        // create a FileWriter in append mode
        FileWriter fw = new FileWriter(ACHIEVEMENT_LIST_FILE_LOCATION, true);
        fw.write(textToAppend);
        fw.close();
    }

}

