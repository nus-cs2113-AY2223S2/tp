package bagpacker.packingfunc;

/**
 * Item class used to store information about item
 * Items to be packed can be saved in PackingList class
 */
public class Item {
    private String itemName;
    private int packedQuantity;
    private int totalQuantity;

    public Item(int quantity, String description) {
        this.itemName = description;
        packedQuantity = 0;
        totalQuantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setPacked(int quantity) {
        this.packedQuantity += quantity;
    }

    public String toString() {
        return "[" + packedQuantity + "/" + totalQuantity + "] " + this.getItemName();
    }
}
