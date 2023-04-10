package seedu.moviemate.movie;

/**
 * Movie class containing the basic essentials of a movie.
 *
 * A movie is categorised by its title (name), titleID (Alphanumeric way to categorise)
 * Year of release, runtime/length (in minutes), and an array of up to 3 Genres.
 * Data is provided by IMDB.
 */
public class Movie implements Comparable<Movie> {
    private final String titleID;
    private final String title;
    private final int year;
    private final int runTimeMinutes;
    private final String[] genres;

    /**
     * Default constructor.
     * @param titleID The Unique ID assigned to a movie.
     * @param title The full title of the movie.
     * @param year The year the movie was made.
     * @param runTimeMinutes The runtime (in minutes).
     * @param genres String[] of Genres, up to 3.
     */
    public Movie(String titleID, String title, int year, int runTimeMinutes, String... genres) {
        this.titleID = titleID;
        this.title = title;
        this.year = year;
        this.runTimeMinutes = runTimeMinutes;
        this.genres = genres;
    }

    public Movie(String[] movieString) {
        this.titleID = movieString[0];
        this.title = movieString[1];
        this.year = Integer.parseInt(movieString[2]);
        this.runTimeMinutes = (int)Double.parseDouble(movieString[3]);
        this.genres = movieString[4].split(",");

    }

    /**
     * Overloaded constructor.
     * @param movie A movie.
     */
    public Movie(Movie movie) {
        this(movie.getTitleID(), movie.getTitle(), movie.getYear(), movie.getRunTimeMinutes(), movie.getGenres());
    }

    /**
     * Default comparator.
     * Sorting rules: Alphabetical title, then year.
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Movie o) {
        return (this.getTitle().equals(o.getTitle())) ?
                (this.getYear() - o.getYear())
                : (this.getTitle().compareTo(o.getTitle()));
    }

    public String getWriteFormat() {
        String splitGenres = this.splitGenres();
        // ID|Title|Year|RunTime|Genres
        return String.format("%s|%s|%d|%d|%s",
                this.titleID,
                this.title,
                this.year,
                this.runTimeMinutes,
                splitGenres);
    }

    public String splitGenres() {
        String output = "";
        for (String genre : this.genres) {
            output += genre + ",";
        }
        // Remove the last comma
        return output.substring(0, output.length() - 1);
    }

    /* Getters are all here below */
    public String getTitleID() {
        return titleID;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getRunTimeMinutes() {
        return runTimeMinutes;
    }

    public String[] getGenres() {
        return genres;
    }

    public String[] getGenresFilter() {
        String[] genresLower = new String[genres.length];
        for (int i = 0; i < genresLower.length; i += 1) {
            genresLower[i] = genres[i].toLowerCase();
        }
        return genresLower;
    }

    public String getGenresString() {
        String genres = "";
        for(String genre : getGenres()){
            genres += genre + ",";
        }
        return " [" + genres.substring(0, genres.length() - 1) + "]";
    }

    @Override
    public String toString() {
        return String.format("%s (%d) %s", this.title, this.year, this.getGenresString());
    }

    /**
     * Get the detail of a movie
     *
     * @return The detail of the movie in a string type, including year, title, genres, and run time minutes
     */
    public String getMovieDetail() {
        String detail = String.format("Title: %s \nYear: %s\nGenres: %s\nRuntime Minutes: %s",
                getTitle() ,getYear(), getGenresString(), getRunTimeMinutes());
        return detail;
    }
}
