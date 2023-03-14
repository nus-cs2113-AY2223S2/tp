import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class MovieDatabase {

    // Movie will be sorted, found by its String name.
    private static TreeMap<String, Movie> movieDatabase;
    private static final int MAX_RELEVANT_MOVIES = 5;

    public MovieDatabase(ArrayList<String[]> movieStrings) throws IOException {
        System.out.print("Loading movie database...");
        TreeMap<String, Movie> movies = new TreeMap<String, Movie>();

        // Get the headers first.
        // Not sure if this is required, but good to have I guess?
        // tconst, primaryTitle, startYear, runtimeMinutes, genres
        String[] headers = movieStrings.get(0);
        if (headers.length < 5) {
            System.out.println("There's something wrong with the CSV file.");
            throw (new IOException());
        }

        // Start from 1 to ignore headers.
        for (int i = 1; i < movieStrings.size(); i++) {
            String[] movieString = movieStrings.get(i);
            Movie movie = new Movie(movieString);

            movies.put(movie.toString(), movie);
        }
        this.movieDatabase = movies;
        System.out.println(" Okay, movie database loaded.");
    }

    /**
     * Find the movies that contain the movie name entered by the user and return them
     *
     * @param userInputMovieName The movie name entered by the user
     * @return return an array list of movies with at most five movies, whose names containing the user input movie name
     */
    public static ArrayList<Movie> find(String userInputMovieName) {
        ArrayList<Movie> relevantMovies = new ArrayList<Movie>();
        Integer movieCount = 0;
        for (Map.Entry<String, Movie> entry : movieDatabase.entrySet()) {
            if (entry.getKey().contains(userInputMovieName)) {
                relevantMovies.add(entry.getValue());
                movieCount += 1;
            }
            if (movieCount.equals(MAX_RELEVANT_MOVIES)) {
                break;
            }
        }
        return relevantMovies;
    }
}
