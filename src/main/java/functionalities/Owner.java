package functionalities;

import exception.SniffException;


public class Owner {
    protected String name;
    protected String contactNumber;

    public Owner(String name, String contactNumber) throws SniffException {
        this.name = setOwnerName(name);
        this.contactNumber = setContactNumber(contactNumber);
    }

    @Override
    public String toString() {
        return name + " | Contact Number: " + contactNumber;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String setContactNumber(String contactNumber) throws SniffException {
        if (!isNumeric(contactNumber)) {
            throw new SniffException(" Contact Number must only contain numbers!");
        }
        else if (contactNumber.length() != 8) {
            throw new SniffException(" Contact Number has to be 8 digits!");
        }
        return contactNumber;
    }

    public String setOwnerName(String name) throws SniffException {
        if (name.isBlank()) {
            throw new SniffException(" Owner Name cannot be empty!");
        }
        else if (!isAlphaSpace(name)) {
            throw new SniffException(" Owner Name must only contain alphabets!");
        }
        return name;
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9]+");
    }

    public static boolean isAlphaSpace(String str) {
        return str != null && str.matches("^[a-zA-Z ]+$");
    }
}
