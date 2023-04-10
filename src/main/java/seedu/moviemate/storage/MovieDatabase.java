package seedu.moviemate.storage;

import java.util.Random;
import seedu.moviemate.movie.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MovieDatabase {

    // Movie will be sorted, found by its String name.
    private static TreeMap<String, Movie> moviesDatabase;
    private static final int MAX_RELEVANT_MOVIES = 5;
    private static final int RANDOM_MOVIES_INTERVAL = 20;

    public MovieDatabase(ArrayList<String[]> movieStrings) throws IOException {
        System.out.print("Loading movie database...");
        TreeMap<String, Movie> movies = new TreeMap<String, Movie>();

        // Get the headers first.
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
        this.moviesDatabase = movies;
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
        for (Map.Entry<String, Movie> entry : moviesDatabase.entrySet()) {
            if (entry.getKey().toLowerCase().contains(userInputMovieName.toLowerCase())) {
                relevantMovies.add(entry.getValue());
                movieCount += 1;
            }
            if (movieCount.equals(MAX_RELEVANT_MOVIES)) {
                break;
            }
        }
        return relevantMovies;
    }

    /**
     * Randomly find some movies that matches the genre entered by the user.
     *
     * @param userInputGenre The string genre entered by the user,
     *                       which they would like to see some random movies of this genre
     * @return An array list containing movie items that are under the userInputGenre.
     */
    public static ArrayList<Movie> randomMovieOfGenres(String userInputGenre) {
        ArrayList<Movie> relevantMovies = new ArrayList<Movie>();
        Integer movieCount = 0;
        int index = 0;
        Random rand = new Random();
        int intInterval = rand.nextInt(RANDOM_MOVIES_INTERVAL);
        if (intInterval == 0) {
            intInterval += 1;
        }
        for (Map.Entry<String, Movie> entry : moviesDatabase.entrySet()) {
            if (index % intInterval == 0
                    && Arrays.asList(entry.getValue().getGenresFilter()).contains(userInputGenre.toLowerCase())) {
                relevantMovies.add(entry.getValue());
                movieCount += 1;
            }
            index += 1;
            if (movieCount.equals(MAX_RELEVANT_MOVIES)) {
                break;
            }
        }
        return relevantMovies;
    }
}
