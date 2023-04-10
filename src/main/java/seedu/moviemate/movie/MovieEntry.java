package seedu.moviemate.movie;

/**
 * User-interacted Movie to be listed in {@link MovieList} MovieList.
 */
public class MovieEntry extends Movie {
    private Review review;

    public MovieEntry(String titleID, String title, int year, int runTimeMinutes, String[] genres) {
        super(titleID, title, year, runTimeMinutes, genres);
        this.review = new Review();
    }

    /**
     * Overloaded constructor...
     * @param movie
     */
    public MovieEntry(Movie movie, Review review) {
        super(movie);
        this.review = review;
    }

    public MovieEntry(Movie movie) {
        super(movie);
        this.review = new Review();
    }

    /**
     * Add (or overwrite) a review for the particular movie listing.
     */
    public void setReview() {
        this.review.setReview();
    }

    public String getReview() {
        return this.review.toString();
    }

    @Override
    public String getWriteFormat() {
        return super.getWriteFormat()
                + String.format("|%s", this.review);
    }

}
