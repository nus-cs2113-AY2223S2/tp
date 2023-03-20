package seedu.duke.budget;

public abstract class GoodsAndServices {
    private int price;

    private int cheap;
    private int moderate;
    private int expensive;
    public GoodsAndServices(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
