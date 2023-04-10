package seedu.duke.budget;

public abstract class GoodsAndServices {
    private int price;

    public GoodsAndServices(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
