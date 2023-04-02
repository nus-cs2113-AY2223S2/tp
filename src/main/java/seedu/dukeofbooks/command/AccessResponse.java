package seedu.dukeofbooks.command;

import seedu.dukeofbooks.data.user.User;

public class AccessResponse {
    public final User user;
    public final String message;
    public AccessResponse(User user, String message) {
        this.user = user;
        this.message = message;
    }
}
