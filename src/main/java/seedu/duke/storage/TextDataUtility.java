package seedu.duke.storage;

import seedu.duke.commons.LogMaster;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class TextDataUtility{
    protected static final String ACHIEVEMENT_LIST_FILE_LOCATION = "allAchievements.txt";
    private static final Logger logger = LogMaster.getLogger(TextDataUtility.class);
    private static final String achievementListText = "Easy Peasy:Complete your first Easy exercises!:0:E:easy:0:1\n" +
            "Easy Peasy Lemon Squeezy:Complete 3 easy exercises!:0:M:easy:0:3\n" +
            "Easy Lemon DESTROYEERRR:Complete 5 easy exercises!:0:H:easy:0:5\n" +
            "Medium Madness:Complete your first Medium exercises!:0:E:medium:0:1\n" +
            "The Medium Maverick:Complete 3 medium exercises!:0:M:medium:0:3\n" +
            "The Medium Mastermind:Complete 5 medium exercises!:0:H:medium:0:5\n" +
            "Hard Hitter:Complete your first Hard exercises!:0:E:hard:0:1\n" +
            "The Hard Hawk:Complete 3 hard exercises!:0:M:hard:0:3\n" +
            "The Hard Hammer:Complete 5 hard exercises!:0:H:hard:0:5\n" +
            "Core Crusher:Complete your first Core exercises!:0:E:core:0:1\n" +
            "The Core Captain:Complete 3 core exercises!:0:M:core:0:3\n" +
            "The Core Conqueror:Complete 5 core exercises!:0:H:core:0:5\n" +
            "Leg Legend:Complete your first Leg exercises!:0:E:legs:0:1\n" +
            "The Leg Luminator:Complete 3 leg exercises!:0:M:legs:0:3\n" +
            "The Leg Lord:Complete 5 leg exercises!:0:H:legs:0:5\n" +
            "Upper Body Unicorn:Complete your first Upper body workout!:0:E:upper:0:1\n" +
            "The Upper Body Warrior:Complete 3 upper body workouts!:0:M:upper:0:3\n" +
            "The Upper Body God/Goddess:Complete 5 upper body workouts!:0:H:upper:0:5\n" +
            "The Gym Novice:Complete your first Gym workout!:0:E:gym:0:1\n" +
            "The Gym Enthusiast:Complete 3 Gym workouts!:0:M:gym:0:3\n" +
            "The Gym Maestro:Complete 5 Gym workouts!:0:H:gym:0:5\n" +
            "Static Starter:Complete your first Static workout!:0:E:static:0:1\n" +
            "The Static Warrior:Complete 3 Static workouts!:0:M:static:0:3\n" +
            "The Static Champion:Complete 5 Static workouts!:0:H:static:0:5";

    public void checkForListData(File listData) {
        if (!listData.exists()) {
            System.out.println("Data file does not exist, creating a new one");
            try {
                if (listData.createNewFile()) {
                    System.out.println("Created a new achievementList file");
                    appendToFile(achievementListText, ACHIEVEMENT_LIST_FILE_LOCATION);
                    logger.log(Level.INFO, "New achievement list file created");
                }
            } catch (IOException e) {
                System.out.println("We can't create a file for some reason :< Please dont use this file thanks");
                logger.log(Level.INFO, "Unable to create achievement list file");
            }
        }
    }

    public void clearAchievementListData(File listData) {
        if (listData.exists()) {
            try {
                new FileWriter(listData).close();
                appendToFile(achievementListText, ACHIEVEMENT_LIST_FILE_LOCATION);
                System.out.println("Cleared achievement data");
                logger.log(Level.INFO, "Achievement data cleared");
            } catch (IOException e) {
                System.out.println("We can't create a file for some reason :< Please dont use this file thanks");
            }
        } else {
            checkForListData(listData);
        }
    }



    private void appendToFile(String textToAppend, String fileLocation) throws IOException {
        FileWriter fw = new FileWriter(fileLocation, true);
        fw.write(textToAppend);
        fw.close();
    }

}
