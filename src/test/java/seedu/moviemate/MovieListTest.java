package seedu.moviemate;

import org.junit.jupiter.api.Test;
import seedu.moviemate.movie.Movie;
import seedu.moviemate.movie.MovieList;
import seedu.moviemate.storage.MovieDatabase;
import seedu.moviemate.storage.Storage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MovieListTest {
    private static MovieDatabase movieDatabase;

    static {
        try {
            movieDatabase = new MovieDatabase(new Storage().loadDatabase());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRemoveByIndex() {

        // Create a new MovieList object with the movie list
        MovieList movieListObj = new MovieList();
        movieListObj.add(MovieDatabase.find("cat").get(0));
        movieListObj.add(MovieDatabase.find("cat").get(1));
        movieListObj.add(MovieDatabase.find("cat").get(2));
        // System.out.println(movieListObj.movieList.size());

        String title1 = movieListObj.movieList.get(0).getTitle();
        String title2 = movieListObj.movieList.get(1).getTitle();
        String title3 = movieListObj.movieList.get(2).getTitle();

        // Remove the second movie (index 1)
        movieListObj.remove(2);

        // Assert that the movie list now contains only 2 movies
        assertEquals(2, movieListObj.movieList.size());

        // Assert that the second movie (index 1) has been removed
        assertEquals(title1, movieListObj.movieList.get(0).getTitle());
        assertEquals(title3, movieListObj.movieList.get(1).getTitle());
    }
    @Test
    public void testList() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);


        MovieList movieListObj = new MovieList();
        movieListObj.add(MovieDatabase.find("t").get(0));
        movieListObj.add(MovieDatabase.find("t").get(1));
        String movie1 = movieListObj.movieList.get(0).toString();
        String movie2 = movieListObj.movieList.get(1).toString();

        // Call the list method and capture the output
        movieListObj.list();
        String output = outputStream.toString();

        // Check that the output is as expected
        String expectedOutput = "1. " + movie1 + "\n" +
                "2. " + movie2 + "\n";
        assertEquals(expectedOutput, output);
    }
    @Test
    public void testGetMovie() {

        // Add some movies to the list
        MovieList movieListObj = new MovieList();
        movieListObj.add(MovieDatabase.find("h").get(0));
        movieListObj.add(MovieDatabase.find("h").get(1));

        // Test getting an existing movie
        Movie actualMovie = movieListObj.getMovie(1);
        assertEquals(movieListObj.movieList.get(0), actualMovie);

        // Test getting a non-existent movie
        assertNull(movieListObj.getMovie(0));
    }
}

