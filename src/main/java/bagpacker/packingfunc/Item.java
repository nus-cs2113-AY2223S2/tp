package bagpacker.packingfunc;
/**
 * Item class used to store information about item
 * Items to be packed can be saved in PackingList class
 */
public class Item {
    private String itemName;
    private boolean isPacked;
    private String packIcon = " ";
    public Item(String description) {
        this.itemName = description;
        isPacked = false;
    }
    public Item(String description, boolean isPacked) {
        this.itemName = description;
        this.isPacked = isPacked;
    }
    public void setPackIcon() {
        if (this.isPacked) {
            this.packIcon = "X";
        } else {
            this.packIcon = " ";
        }
    }

    public String getItemName() {
        return itemName;
    }

    public boolean getIsPacked() {
        return isPacked;
    }
    public void setPacked(boolean packed) {
        isPacked = packed;
        setPackIcon();
    }

    public String toString() {
        return "[" + this.packIcon + "] " + this.getItemName();
    }
}
