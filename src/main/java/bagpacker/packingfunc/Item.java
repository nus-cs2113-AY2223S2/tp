package bagpacker.packingfunc;
/**
 * Item class used to store information about item
 * Items to be packed can be saved in PackingList class
 */
public class Item {
    private String itemName;
    private boolean isPacked;
    public Item(String description) {
        this.itemName = description;
        isPacked = false;
    }
    public Item(String description, boolean isPacked) {
        this.itemName = description;
        this.isPacked = isPacked;
    }
    
    public String getItemName() {
        return itemName;
    }

    public boolean isPacked() {
        return isPacked;
    }
    public void setPacked(boolean packed) {
        isPacked = packed;
    }

    public String toString() {
        return "[" + this.isPacked() + "] " + this.getItemName();
    }
}
