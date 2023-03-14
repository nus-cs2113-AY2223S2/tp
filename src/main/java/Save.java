import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Save {

    //method that saves current content of movieList/ToWatchList into a text file
    //For each movie, content occupies one line in the text file like this:
    // TitleID/Title/Year/RunTimeMinutes/Genre1/Genre2/Genre3
    public Save(String filepath, ArrayList<Movie> list) {
        String saveData = "";
        for (Movie movie : list) {
            saveData = saveData + movie.getTitleID() + "/";
            saveData = saveData + movie.getTitle() + "/";
            saveData = saveData + movie.getYear() + "/";
            saveData = saveData + movie.getRunTimeMinutes() + "/";
            String[] genreArr = movie.getGenres();
            for (String genre : genreArr) {
                saveData = saveData + genre + "/";
            }
            saveData = saveData + "\n";
        }
        try {
            writeToFile(filepath, saveData);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void writeToFile(String filepath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write(textToAdd);
        fw.close();
    }
}
