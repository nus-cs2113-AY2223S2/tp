package functionalities;

import exception.SniffException;

/**
 * The Owner class represents the owner of the animal that is involved in the appointment and contains their name and
 * contact number descriptions. This class is mainly used to gather information for the appointments.
 */

public class Owner {
    protected String name;
    protected String contactNumber;

    /**
     * Constructs a new Owner object with the specified parameters.
     *
     * @param name          The name of the owner.
     * @param contactNumber the contact number of the owner.
     * @throws SniffException if contact number is of the wrong format.
     */
    public Owner(String name, String contactNumber) throws SniffException {
        this.name = setOwnerName(name);
        this.contactNumber = setContactNumber(contactNumber);
    }

    @Override
    public String toString() {
        return name + " | Contact Number: " + contactNumber;
    }

    @Override
    public boolean equals(Object o) {
        Owner other = (Owner) o;
        return this.name.equals(other.name) && this.contactNumber.equals(other.contactNumber);
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Sets the contact number of the owner to the correct format.
     *
     * @param contactNumber the contact number of the owner.
     * @return the contact number with the correct format.
     * @throws SniffException if the contact number is of the wrong format.
     */
    public String setContactNumber(String contactNumber) throws SniffException {
        if (contactNumber.isBlank()) {
            throw new SniffException(" Contact Number cannot be empty!");
        } else if (!isNumeric(contactNumber)) {
            throw new SniffException(" Contact Number must only contain numbers!");
        } else if (contactNumber.length() != 8) {
            throw new SniffException(" Contact Number has to be 8 digits!");
        }
        return contactNumber;
    }

    /**
     * Sets the name of the owner to the correct format.
     *
     * @param name the name of the owner.
     * @return the name with the correct format.
     * @throws SniffException if the name is of the wrong format.
     */
    public String setOwnerName(String name) throws SniffException {
        if (name.isBlank()) {
            throw new SniffException(" Owner Name cannot be empty!");
        } else if (!isAlphaSpace(name)) {
            throw new SniffException(" Owner Name must only contain alphabets!");
        }
        return name;
    }

    /**
     * Checks if the given string is strictly numeric.
     *
     * @param str the input string.
     * @return true if the input string is not null and is numeric, false otherwise.
     */
    public static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9]+");
    }

    /**
     * Check if the given string is strictly alphabetic and contains spaces.
     *
     * @param str the input string.
     * @return true if the input string is not null and is alphabetic and contains space, false otherwise.
     */
    public static boolean isAlphaSpace(String str) {
        return str != null && str.matches("^[a-zA-Z ]+$");
    }
}
