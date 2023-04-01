package bagpacker.packingfunc;

/**
 * Item class used to store information about item
 * Items to be packed can be saved in PackingList class
 */
public class Item {
    private final String itemName;

    public int getPackedQuantity() {
        return packedQuantity;
    }

    private int packedQuantity;

    public int getTotalQuantity() {
        return totalQuantity;
    }

    private final int totalQuantity;

    public Item(int quantity, String description) {
        this.itemName = description;
        packedQuantity = 0;
        this.totalQuantity = quantity;
    }

    //    public Item(String description, boolean isPacked) {
    //        this.itemName = description;
    //        this.isPacked = isPacked;
    //    }

    //    public void setPackIcon() {
    //        if (this.isPacked) {
    //            this.packIcon = "X";
    //        } else {
    //            this.packIcon = " ";
    //        }
    //    }

    public String getItemName() {
        return itemName;
    }

    //    public boolean getIsPacked() {
    //        return isPacked;
    //    }
    public int getTotalQuantity(){
        return totalQuantity;
    }
    public void setPacked(int quantity) {
        this.packedQuantity += quantity;
    }

    public void setUnpacked(int quantity) {
        this.packedQuantity -= quantity;
    }

    public String toString() {
        return "[" + packedQuantity + "/" + totalQuantity + "] " + this.getItemName();
    }
}
