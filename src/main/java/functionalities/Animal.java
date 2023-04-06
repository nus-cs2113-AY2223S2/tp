package functionalities;

import exception.SniffException;

public class Animal {
    protected String type;
    protected String name;

    public Animal(String type, String name) throws SniffException {
        this.type = setType(type);
        this.name = setName(name);
    }

    @Override
    public String toString() {
        return name + " | Animal Type: " + type;
    }

    public String getAnimalName() {
        return name;
    }

    public String getAnimalType() {
        return type;
    }

    public String setName(String name) throws SniffException {
        if (name.isBlank()) {
            throw new SniffException(" Animal Name cannot be empty!");
        }
        if (!isAlphaSpace(name)) {
            throw new SniffException(" Animal Name must only contain alphabets!");
        }
        return name;
    }

    public String setType(String type) throws SniffException {
        if (type.isBlank()) {
            throw new SniffException(" Animal Type cannot be empty!");
        }
        if (!isAlphaSpace(type)) {
            throw new SniffException(" Animal Type must only contain alphabets!");
        }
        return type;
    }

    public static boolean isAlphaSpace(String str) {
        return str != null && str.matches("^[a-zA-Z ]+$");
    }
}
