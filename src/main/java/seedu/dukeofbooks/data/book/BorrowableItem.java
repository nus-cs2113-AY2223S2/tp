package seedu.dukeofbooks.data.book;

import seedu.dukeofbooks.data.exception.DuplicateActionException;


public abstract class BorrowableItem {
    protected boolean isBorrowed;

    public BorrowableItem() {
        isBorrowed = false;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }


    public void returnBook() throws DuplicateActionException {
        if (!isBorrowed) {
            throw new DuplicateActionException(
                    "Cannot borrow a not borrowed book");
        }
        isBorrowed = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof BorrowableItem)) {
            return false;
        } else {
            return isBorrowed == ((BorrowableItem) obj).isBorrowed;
        }
    }

    @Override
    public String toString() {
        return "Status: " + (isBorrowed ? "Borrowed" : "Available");
    }
}
