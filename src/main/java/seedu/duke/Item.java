package seedu.duke;
import java.util.ArrayList;

public class Item {
    private String upc;
    private String name;
    private Double price;
    private Integer quantity;
    private String category;
    private ArrayList<String> tags = new ArrayList<>();

    Item(String name, String upc, String qty, String price) {
        this.name = name;
        this.upc = upc;
        this.price = Double.parseDouble(price);
        this.quantity = Integer.parseInt(qty);
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /** Retrieves the quantity of the item.*/
    public Integer getQuantity() {
        return quantity;
    }

    /** Updates the quantity of the item.*/
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
