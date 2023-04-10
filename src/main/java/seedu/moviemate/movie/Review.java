package seedu.moviemate.movie;

import seedu.moviemate.parser.Parser;

import java.util.Scanner;

/**
 * A review encompasses a string (Text review).
 * and a star review (0 to 5 scale).
 */
public class Review implements Comparable<Review> {
    private static String REVIEW_INSTRUCTIONS = "Write your review. Use as many lines as you need. "
            + "To end, simply input a blank line.";
    private String reviewText;
    private int reviewStars;


    /**
     * Review constructor. If either parameter is missing, it will construct with blank ones.
     * @param reviewText String review in text.
     * @param reviewStars Integer review (0 to 5 stars)
     */
    public Review(String reviewText, int reviewStars) {
        this.reviewText = reviewText;
        this.reviewStars = reviewStars;
    }

    public Review(String reviewText) {
        this.reviewText = reviewText;
        this.reviewStars = 0;
    }

    public Review(int reviewStars) {
        this.reviewText = "";
        this.reviewStars = reviewStars;
    }

    public Review() {
        this.reviewText = "";
        this.reviewStars = 0;
    }

    /**
     * Set review for both text and stars.
     */
    public void setReview() {
        // Set Review Text First
        this.setReviewText();
        System.out.println("Text review added.");

        this.setReviewStars();
        System.out.println("Star review added.");
    }

    /**
     * Sets the star review from 0 to 5 (Inclusive).
     * Feedback to user (No change) if stars are not valid.
     * @param stars star rating of 0 to 5 inclusive.
     */
    public void setReviewStars(int stars) {
        // Bad path
        if (stars > 5 || stars < 0) {
            System.out.println("Hey! Reviews only from 0 to 5!");
        } else {
            this.reviewStars = stars;
            assert this.reviewStars == stars;
        }
    }

    private void setReviewStars() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("Please rate the movie [0 to 5]: ");
            String starsString = scan.nextLine();

            int stars = Parser.parseIndex(starsString, 0, 5);
            if (stars < 0) {
                System.out.println("Invalid input! Please rate again.");
                continue;
            }

            this.reviewStars = stars;
            return;
        }

    }

    /**
     * @return The review stars (integer 0 to 5).
     */
    public int getReviewStars() {
        return this.reviewStars;
    }

    /**
     * Sets the review text, which actually requires scanner.
     */
    private void setReviewText() {
        Scanner scan = new Scanner(System.in);
        String review = "";

        System.out.println(REVIEW_INSTRUCTIONS);

        // Stop when empty.
        while (true) {
            System.out.print("> ");
            String line = scan.nextLine();

            // has next as long as it's not whitespace only.
            if (line.trim().isEmpty()) {
                break;
            }
            review += line;
        }
        this.reviewText = review;
    }

    /**
     * @return The text review.
     */
    public String getReviewText() {
        return this.reviewText;
    }

    /**
     * Clears the review text to blank string.
     */
    public void deleteReviewText() {
        this.reviewText = "";
    }

    @Override
    public String toString() {
        return String.format("Review: "
                + "%s"
                + "(%d/5 stars)",
                this.reviewText, this.reviewStars);
    }
    
    /**
     * Simply sort based on star value.
     */
    @Override
    public int compareTo(Review otherReview) {
        return (this.reviewStars - otherReview.getReviewStars());
    }
}
