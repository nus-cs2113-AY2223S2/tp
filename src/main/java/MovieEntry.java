import java.util.Date;

/**
 * User-interacted Movie to be listed in {@link MovieList} MovieList.
 */
public class MovieEntry extends Movie {
    private Date dateWatched;
    private String review;

    public MovieEntry(String titleID, String title, int year, int runTimeMinutes, String[] genres) {
        super(titleID, title, year, runTimeMinutes, genres);
        this.review = "";
    }

    /**
     * Overloaded constructor...
     * @param movie
     */
    public MovieEntry(Movie movie, String review) {
        super(movie);
        this.review = review;
    }

    /**
     * Add (or overwrite) a review for the particular movie listing.
     * @param review A string for your review.
     */
    public void setReview(String review) {
        this.review = review;
    }

    public void deleteReview(String review) {
        this.review = "";
    }

    public String getReview() {
        return this.review;
    }

    /**
     * Checks if there is an existing review.
     * @return True if the review is not null and empty.
     */
    public boolean hasReview() {
        return (this.review != null && this.review != "");
    }

    @Override
    public String getWriteFormat() {
        return super.getWriteFormat()
                + String.format("|%s", this.review);
    }


}
