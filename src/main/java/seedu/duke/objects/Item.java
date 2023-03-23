package seedu.duke.objects;

import java.util.ArrayList;
import java.util.Objects;

public class Item {
    private String upc;
    private String name;
    private Double price;
    private Integer quantity;
    private String category = "Uncategorized";
    private ArrayList<String> tags = new ArrayList<>();

    public Item(String name, String upc, String qty, String price) {
        this.name = name;
        this.upc = upc;
        this.price = Double.parseDouble(price);
        this.quantity = Integer.parseInt(qty);
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
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

    /**
     * Retrieves the quantity of the item.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Updates the quantity of the item.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isUpdatedFrom(Item oldItem) {
        int itemsChanged = 0;
        if (!Objects.equals(oldItem.getName(), this.getName())) {
            itemsChanged += 1;
        }
        if (!Objects.equals(oldItem.getQuantity(), this.getQuantity())) {
            itemsChanged += 1;
        }
        if (!Objects.equals(oldItem.getPrice(), this.getPrice())) {
            itemsChanged += 1;
        }
        return itemsChanged != 0;
    }

    @Override
    public String toString() {
        String returnString = "Name: " + name + '\n' + "UPC: " + upc + '\n' + "Price: " + price + '\n'
                + "Quantity: " + quantity + '\n' + "Category: " + category;
        if (!tags.isEmpty()) {
            returnString += "\nTags: ";
        }
        for (int i = 0; i < tags.size(); i++) {
            returnString += tags.get(i);
            if (i < tags.size() - 1) {
                returnString += ", ";
            }
        }
        return returnString;
    }

    @Override
    public boolean equals(Object o) {
        if (!o.getClass().equals(this.getClass())) {
            return false;
        }
        Item item = (Item) o;
        return item.getUpc().equals(upc);
    }

}
