package seedu.dukeofbooks.data.user;

import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.person.Person;
import seedu.dukeofbooks.data.person.PersonName;
import seedu.dukeofbooks.data.person.Phone;

public class User extends Person {
    private final String username;
    // for security reason, password is not explicitly stored
    private int passwordHash;
    private boolean isSuperUser;

    public User(String username, String password, String name) throws IllegalValueException {
        super(name);
        this.username = username;
        this.passwordHash = password.hashCode();
        this.isSuperUser = false;
    }

    public User(String username, String password,String name, int phone)
            throws IllegalValueException {
        super(new PersonName(name), new Phone(phone));
        this.username = username;
        this.passwordHash = password.hashCode();
        this.isSuperUser = false;
    }

    public String getUsername() {
        return username;
    }

    public boolean isSuperUser() {
        return isSuperUser;
    }

    public void setSuperUser(boolean isSuperUser) {
        this.isSuperUser = isSuperUser;
    }

    public boolean changePassword(String prevPassword, String newPassword) {
        if (!verifyPassword(prevPassword)) {
            return false;
        }
        if (prevPassword.equals(newPassword)) {
            return false;
        } else {
            passwordHash = newPassword.hashCode();
            return true;
        }
    }

    public boolean verifyPassword(String toCheck) {
        return passwordHash == toCheck.hashCode();
    }

}
