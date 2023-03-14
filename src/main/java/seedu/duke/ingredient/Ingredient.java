package seedu.duke.ingredient;

public class Ingredient {
    protected String name;
    protected double quantity;

    public Ingredient(String name, double quantity) {
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

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }
}
