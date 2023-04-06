package seedu.duke.data.userdata;

import java.util.ArrayList;

import seedu.duke.data.ipptcalculator.UserScore;
import seedu.duke.ui.PrintExercises;

/**
 * This class consists of all user sessions that the user has completed in FitnessDuke and handles the addition of
 * new sessions made by the user
 */
public class UserCareerData {

    /**
     * ArrayList of Session that consists of all sessions completed by the user
     */
    private ArrayList<Session> totalUserCareerSessions;

    /**
     * Constructs an empty userCareer
     */
    public UserCareerData () {
        totalUserCareerSessions = new ArrayList<>();
    }

    /**
     * Gets all the user sessions completed in their career
     *
     * @return An arrayList consisting of all sessionData completed by the user
     */
    public ArrayList<Session> getTotalUserCareerSessions () {
        return totalUserCareerSessions;
    }

    /**
     * Adds a new workout session that has been completed by the user
     *
     * @param session Data of the session that has been completed
     */
    public void addWorkoutSession (Session session) {
        totalUserCareerSessions.add(session);
    }

    //@@author L-K-Chng
    /**
     * Deletes a workout session according to the session number as
     * input by the user.
     * @param i the session number input by the user.
     */
    public void deleteWorkoutSession (int i) {
        totalUserCareerSessions.remove(i-1);
    }

    //@@author ChubbsBunns
    public void printAllFinishedWorkoutSessions () {
        if (totalUserCareerSessions.isEmpty()) {
            System.out.println("You have not completed any exercises ☹");
            System.out.println("Add on to this list by completing a workout session!");
        } else {
            PrintExercises exercisePrinter = new PrintExercises();
            for (int i = 0; i < totalUserCareerSessions.size(); i++) {
                Session sessionInList = totalUserCareerSessions.get(i);
                System.out.println("Session " + (i + 1));
                String dateTime = sessionInList.getDateAdded().toString();
                String[] dateSplit = dateTime.split("T", 2);
                assert dateSplit.length == 2;
                System.out.println("On this date: " + dateSplit[0]);
                exercisePrinter.printExercise(sessionInList.getSessionExercises());
                if (i != totalUserCareerSessions.size() - 1) {
                    System.out.println("\n ");
                }
                if (sessionInList instanceof IPPTSession){
                    UserScore sessionScore = ((IPPTSession) sessionInList).getUserScore();
                    System.out.println("You scored at total of: " + sessionScore.getTotalScore() + '\n' +
                            "Pushups: " + sessionScore.getPushupScore() + '\n' +
                            "Situps: " + sessionScore.getSitupScore() + '\n' +
                            "2.4 Km Run " + sessionScore.getRunScore());
                }
            }
        }
    }
}
