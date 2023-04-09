package common;



public class AccountMessage {
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final String NO_SPECIAL_CHARACTERS_MESSAGE = "Username must contain only letters and numbers.";
    public static final String NO_NULL_PASSWORD_MESSAGE = "Password must be specified.";
    public static final String PASSWORD_LENGTH_ERROR_MESSAGE = "Password must be at least " + MIN_PASSWORD_LENGTH +
            " characters long.";
    public static final String USERNAME_IS_TAKEN_MESSAGE = "The username is taken, please use another username.";
    public static final String IO_EXCEPTION_SIGNUP_MESSAGE = "Error: Failed to create account file.";
    public static final String VALID_ACCOUNT_MESSAGE = "Login successfully.";
    public static final String INVALID_ACCOUNT_MESSAGE = "Invalid username or password.";
    public static final String NON_EXISTING_ACCOUNT_MESSAGE = "Sorry, there is no username found.";
    public static final String IO_EXCEPTION_LOGIN_MESSAGE = "An error occurred while logging in.";
    public static final String FAIL_READING_USERNAME_MESSAGE = "An error occurred while logging in.";
    public static final String SAVED_MESSAGE = "Saved successfully.";
}
