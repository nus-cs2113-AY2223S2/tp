package seedu.moviemate;

import org.junit.jupiter.api.Test;
import seedu.moviemate.movie.Review;

public class ReviewTest {

    private Review review;

    @Test
    public void setReviewStars_outOfBounds_noChange() throws NumberFormatException {
        review = new Review();
        review.setReviewStars(3);
        assert review.getReviewStars() == 3;

        review.setReviewStars(6);
        assert review.getReviewStars() == 3;
    }


}
