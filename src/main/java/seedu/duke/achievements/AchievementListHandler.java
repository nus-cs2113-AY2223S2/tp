package seedu.duke.achievements;

import org.w3c.dom.Text;
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
    }

    private void pushDataToAchievementList(ArrayList<Achievement> achievementListList, File achievementListData){
        try{
            Scanner listDataScanner = new Scanner(achievementListData);
        } catch (FileNotFoundException e) {

        }


}



}
