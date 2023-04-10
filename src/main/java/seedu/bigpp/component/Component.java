package seedu.bigpp.component;

public class Component {
    protected String name;
    protected String brand;
    protected float price;

    public String getName() {
        return this.name;
    }

    public String getBrand() {
        return this.brand;
    }

    public float getPrice() {
        return this.price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String toString(Boolean getDetails) {
        if (getDetails) {
            return "NAME: " + this.name + "\nBRAND: " + this.brand + "\nPRICE: " + this.price;
        } else {
            return this.name;
        }
    }
}
