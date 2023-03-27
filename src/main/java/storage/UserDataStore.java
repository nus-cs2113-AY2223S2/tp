package storage;
import java.util.HashMap;
import java.util.Map;

/** Class for storing user data in memory using a map. */
public final class UserDataStore {

    /** Map of usernames to their passwords. */
    private final Map<String, String> userMap;

    /** Singleton instance of UserDataStore. */
    private static final UserDataStore INSTANCE = new UserDataStore();

    /** Private constructor to prevent instantiation from outside the class. */
    private UserDataStore() {
        userMap = new HashMap<>();
    }

    /** Returns the singleton instance of UserDataStore. */
    public static UserDataStore getInstance() {
        return INSTANCE;
    }

    /**
     * Checks if a username is already taken.
     *
     * @param username the username to check
     * @return true if the username is taken, false otherwise
     */
    public boolean isUsernameTaken(String username) {
        return userMap.containsKey(username);
    }

    /**
     * Registers a new user with the given username and password.
     *
     * @param username the username of the new user
     * @param password the password of the new user
     */
    public void registerUser(String username, String password) {
        userMap.put(username, password);
    }

    /**
     * Checks if a login attempt with the given username and password is correct.
     *
     * @param username the username of the user attempting to log in
     * @param password the password of the user attempting to log in
     * @return true if the login attempt is correct, false otherwise
     */
    public boolean isLoginCorrect(String username, String password) {
        // Username isn't registered
        if (!userMap.containsKey(username)) {
            return false;
        }

        String storedPassword = userMap.get(username);
        return password.equals(storedPassword);
    }
}
