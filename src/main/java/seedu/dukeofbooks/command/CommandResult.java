package seedu.dukeofbooks.command;

import java.util.List;

import seedu.dukeofbooks.data.book.Book;

public class CommandResult {
        /** The feedback message to be shown to the user. Contains a description of the execution result */
        public final String feedbackToUser;

        /** The list of book that was produced by the command */
        private final List<Book> relevantBook;
    
        public CommandResult(String feedbackToUser) {
            this.feedbackToUser = feedbackToUser;
            relevantBook = null;
        }
    
        public CommandResult(String feedbackToUser, List<Book> relevantBook) {
            this.feedbackToUser = feedbackToUser;
            this.relevantBook = relevantBook;
        }
    
        /**
         * Returns a list of Book that was produced by the command, if any.
         */
        public List<Book> getRelevantBook() {
            return relevantBook;
        }
}
