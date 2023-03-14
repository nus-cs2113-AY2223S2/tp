package seedu.duke.food;

public class Food {
    private String name;
    private String expiryDate;

    //private int quantity;
    public Food(String name, String expiryDate) {
        this.name = name;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String printFoodDetails() {
        String details = "Product Name: " + getName() + "\n" + "Expired by: " + getExpiryDate();
        return details;
    }

    @Override
    public String toString() {
        return getName() + "\n       Expiry date: " + getExpiryDate();
    }
}
