package seedu.securenus.secrets;

import seedu.securenus.Backend;
import seedu.securenus.SecureNUSLogger;

import java.util.logging.Level;

/**
 * Represents a NUSNet secret that stores the user's NUSNet ID and password.
 * Inherits from the Secret class.
 */
public class NUSNet extends Secret{

    public static final String TYPE = "NUSNet";
    private static final String ALLOWED_ID_REGEX = "(e|E)\\d{7}";
    private String nusNetId;
    private String password;


    /**
     * Constructor for creating a NUSNet secret with a folder name.
     *
     * @param name Name of the NUSNet account.
     * @param folderName Name of the folder that the NUSNet account belongs to.
     * @param nusNetId NUSNet ID of the account.
     * @param password Password of the account.
     */
    public NUSNet(String name, String folderName, String nusNetId, String password) {
        super(name, folderName);
        this.nusNetId = nusNetId;
        this.password = password;
    }

    /**
     * Constructor for creating a NUSNet secret without a folder name.
     *
     * @param name Name of the NUSNet account.
     * @param nusNetId NUSNet ID of the account.
     * @param password Password of the account.
     */
    public NUSNet(String name, String nusNetId, String password) {
        super(name, "unnamed");
        this.nusNetId = nusNetId;
        this.password = password;
    }

    public String getType() {
        return TYPE;
    }

    public static boolean isLegalId(String nusNetId) {
        if (!nusNetId.matches(ALLOWED_ID_REGEX)) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, NUSNet ID is illegal, " + nusNetId);
        }
        return nusNetId.matches(ALLOWED_ID_REGEX);
    }
    /**
     * Returns the password of the NUSNet account
     *
     * @return The password of the NUSNet account.
     */
    public String getPassword() {
        return password;
    }

    public void setNusNetId(String nusNetId) {
        this.nusNetId = nusNetId;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Returns the NUSNet ID of the NUSNet account
     *
     * @return The NUSNet ID of the NUSNet account.
     */
    public String getNusNetId() {
        return nusNetId;
    }

    /**
     * Returns a formatted string that represents the NUSNet secret in a form that can be stored in the database.
     *
     * @return The formatted string.
     */
    @Override
    public String toStringForDatabase() {
        String formattedString =  "nusNetId," + super.toStringForDatabase() +
                "," + this.nusNetId + "," + Backend.encode(this.password);
        return formattedString;
    }

    /**
     * Returns a string that reveals the name, NUSNet ID and password of the NUSNet account.
     *
     * @return The string that reveals the NUSNet secret.
     */
    @Override
    public String getRevealStr() {
        return String.format("Name: %s\n" +
                        "NUSNet ID: %s\n" +
                        "Password: %s",
                getName(), nusNetId, password);
    }
}
