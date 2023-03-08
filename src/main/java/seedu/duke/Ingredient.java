package seedu.duke;

public class Ingredient {
    protected String name;
    protected int quantity;

    public Ingredient(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return this.quantity + " " + this.name;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
