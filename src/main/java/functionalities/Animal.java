package functionalities;

public class Animal {
    protected String type;
    protected String name;

    public Animal(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " | Animal Type: " + type;
    }

    @Override
    public boolean equals(Object o) {
        Animal other = (Animal) o;
        return this.name.equals(other.name) && this.type.equals(other.type);
    }
    public String getAnimalName() {
        return name;
    }

    public String getAnimalType() {
        return type;
    }
}
