package seedu.moviemate.movie;

import seedu.moviemate.parser.Parser;
import seedu.moviemate.storage.MovieDatabase;
import seedu.moviemate.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * MovieList class containing the list of movies.
 * A movie list can be either a watched list or a to-watch list.
 */
public class MovieList {
    public ArrayList<Movie> movieList;

    /**
     * Default constructed for empty MovieList.
     */
    public MovieList() {
        this.movieList = new ArrayList<Movie>();
    }

    /**
     * Overloaded constructor with filled MovieList.
     *
     * @param movieList List of movies.
     */
    public MovieList(Collection<Movie> movieList) {
        this.movieList = new ArrayList<Movie>(movieList);
    }

    public MovieList(ArrayList<String[]> movieStrings) {
        this.movieList = new ArrayList<Movie>();
        for (String[] movieString : movieStrings) {
            this.movieList.add(createMovie(movieString));
        }
    }

    /**
     * Checks if related movie list is empty. For ease of use when calling.
     *
     * @return true if movie list is empty
     */
    public boolean empty() {
        return this.movieList.isEmpty();
    }

    /**
     * Gets the index of a movie in the movieList
     * Also can be used to check if a movie is inside the list at all
     *
     * @param movie to get the index of
     * @return index in list, -1 if movie is not found
     */
    public int getIndex(Movie movie) {
        //individual elements of input movie are compared against those in every movie of list
        int index = 1;
        for (Movie mv : this.movieList) {
            if ((mv.getTitle()).equals(movie.getTitle())
                && mv.getYear() == movie.getYear()
                    && mv.getRunTimeMinutes() == movie.getRunTimeMinutes()
                        && (mv.getGenresString()).equals(movie.getGenresString())) {
                return index;
            }
            index += 1;
        }
        return -1;
    }


    /**
     * Used for see detail by movie name.
     * The function will search in the database for the most relevant movies and then prompt
     * the user to enter the index of the movie.
     * It will then show the movie detail to the user
     *
     * @param inputTitle Movie name entered by the user
     */
    public static void findMovieDetail(String inputTitle, Ui ui) {
        ArrayList<Movie> relevantMovies = MovieDatabase.find(inputTitle);
        if (relevantMovies.size() == 0) {
            System.out.println("No relevant movie found, please try entering the movie name again!");
            return;
        }
        if (inputTitle.isBlank()) {
            System.out.println("<Movie Name> cannot be left blank. Please try entering " +
                    "the seedetail movie command and movie name again!");
            return;
        }
        int id = 1;
        for (Movie relevantMovie : relevantMovies) {
            System.out.println(id + ". " + relevantMovie.toString());
            id += 1;
        }
        ui.printPromptIndexForDetail();

        String input = ui.inputCommand();
        Movie movie;
        while (true) {
            int index = Parser.parseIndex(input, 1, relevantMovies.size());
            if (index < 0) {
                ui.printRequireValidIndex(1, relevantMovies.size());
                input = ui.inputCommand();
            } else if (index == 0) {
                ui.printExitInputIndex();
                return;
            } else {
                movie = relevantMovies.get(index - 1);
                if (movie.getMovieDetail() != null) {
                    System.out.println(movie.getMovieDetail());
                    ui.printLine();
                    ui.printSeedetailSuccess();
                    return;
                }
                ui.printSeedetailFail();
                break;
            }
        }
    }

    /**
     * Add a movie to the contained list.
     *
     * @param movie a movie.
     */
    public void add(Movie movie) {
        this.movieList.add(movie);
    }

    /**
     * This is a simple add to list that's meant to be overridden.
     * NOTE: Dev tried to fix this to uphold programming principles but stopped for lack of time.
     * This should be an abstract method and class by right, but there are too many bugs to resolve.
     *
     * @param inputTitle Title of Movie to look up
     * @param watchedList List of Watched movies
     * @param toWatchList List of To-Watch movies
     * @param ui The UI
     */
    public void addToList(
            String inputTitle, MovieList watchedList, MovieList toWatchList, Ui ui) {
        ArrayList<Movie> relevantMovies = MovieDatabase.find(inputTitle);
        if (relevantMovies.size() == 0) {
            System.out.println("No relevant movie found, please try entering the towatch " +
                    "command and movie name again!");
            return;
        }
        if (inputTitle.isBlank()) {
            System.out.println("<Movie Name> cannot be left blank, please try entering " +
                    "the towatch command and movie name again!");
            return;
        }
        int id = 1;
        for (Movie relevantMovie : relevantMovies) {
            System.out.println(id + ". " + relevantMovie.toString());
            id += 1;
        }
        ui.printPromptIndexForAdd();

        String input = ui.inputCommand();
        Movie movie;
        while (true) {
            int addIndex = Parser.parseIndex(input, 1, relevantMovies.size());
            if (addIndex < 0) {
                ui.printRequireValidIndex(1, relevantMovies.size());
                input = ui.inputCommand();
                continue;
            }
            if (addIndex == 0) {
                ui.printExitInputIndex();
                return;
            }
            // happy path
            movie = relevantMovies.get(addIndex - 1);
            break;
        }
        watchedList.add(movie);
        Ui.showAddMovieMessage(movie.toString());
    };

    /**
     * Prints all the movies inside.
     */
    public void list() {
        int i = 1;
        for (Movie movie : movieList) {
            System.out.printf("%d. %s\n", i, movie.toString());
            i++;
        }
    }

    /**
     * Lists out all movies in watched and to-watch lists containing the input genre
     *
     * @param genre input movie genre to look up
     */
    public void filter(String genre) {
        if (genre.isBlank()) {
            System.out.println("Genre description cannot be left blank. Please try entering " +
                    "the filter command and genre again!");
            return;
        }
        String classname = getClass().getName();
        if (classname.equals("seedu.moviemate.movie.WatchedList")) {
            System.out.println("In watched list:");
        } else if (classname.equals("seedu.moviemate.movie.ToWatchList")) {
            System.out.println("In to-watch list:");
        }
        int i = 1;
        for (Movie movie : movieList) {
            if (Arrays.asList(movie.getGenresFilter()).contains(genre.toLowerCase())) {
                System.out.printf("%d. %s\n", i, movie.toString());
                i++;
            }
        }
        if (i == 1) {
            System.out.println("There are no movies of this genre in this list");
        }
    }

    /**
     * Remove a specific movie from the contained list.
     *
     * @param movie a movie.
     */
    public void remove(Movie movie) {
        this.movieList.remove(movie);
    }

    /**
     * Remove a movie from the contained list.
     *
     * @param index 1-indexed index of the movie in list.
     */
    public void remove(int index) {
        index = index - 1; // Offset 1-index
        try {
            this.movieList.remove(index);
            Ui.showDeleteMessage();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Movie id out of range");
        }
    }

    /**
     * Initializes an object in the Movie class
     *
     * @param movieStrings all the information required to be stored in Movie, as a single string
     * @return initialized Movie
     */
    public Movie createMovie(String[] movieStrings) {
        String id = movieStrings[0];
        String title = movieStrings[1];
        int year = Integer.parseInt(movieStrings[2]);
        int runTime = Integer.parseInt(movieStrings[3]);
        String genreStrings = movieStrings[4];
        String[] genres = parseGenres(genreStrings);

        assert movieStrings != null : "Movie strings array cannot be null";
        assert movieStrings.length == 5 : "Movie strings array must have length of 5";
        assert id != null : "Movie ID cannot be null";
        assert title != null : "Movie title cannot be null";
        assert genreStrings != null : "Movie genres string cannot be null";

        Movie movie = new Movie(id, title, year, runTime, genres);
        return movie;
    }

    /**
     * Parses genre array to be represented in the format: [genre1,genre2,genre3]
     *
     * @param genreStrings all genres in the array listed as a single string
     * @return genre array parsed to the correct format
     */
    private String[] parseGenres(String genreStrings) {
        String[] genres = genreStrings.split(",");
        return genres;
    }

    public String getFileWriteFormat() {
        String output = "";
        for (Movie movie : this.movieList) {
            output += movie.getWriteFormat() + '\n';
        }
        return output;
    }

    /**
     * To be used by user-facing operations to automatically account for indexing problems.
     *
     * @param index index in list of desired movie
     * @return movie at correct index in list
     */
    public Movie getMovie(int index) {
        int i = index - 1;
        try {
            return this.movieList.get(i);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("I don't think you got the right movie number.\n" +
                    "Please try entering the command again");
            e.getMessage();
        }

        return null;
    }

    @Override
    public String toString() {
        return this.movieList.toString();
    }

    /**
     * Get the detail of the movie in the movie list by index.
     *
     * @param index The index of the movie for getting detail
     * @return the detail of the movie in string type
     */
    public String getMovieDetail(int index) {
        int finalIndex = index - 1;
        Movie movie = this.movieList.get(finalIndex);

        String detail = movie.getMovieDetail();
        return detail;
    }
}
