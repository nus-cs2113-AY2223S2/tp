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
    public int getUnpackedQuantity() {
        return totalQuantity - packedQuantity;
    }

    /**
     * Constructor of an Item class used in @AddCommand
     * @param quantity no. of items user wants to pack
     * @param description name of the item
     */
    public Item(int quantity, String description) {
        this.itemName = description;
        packedQuantity = 0;
        this.totalQuantity = quantity;
    }

    /**
     * Constructor of an Item class used in @Storage
     * @param totalQuantity total no. of items needed to pack shown in save file
     * @param packedQuantity no. of items currently packed shown in the save file
     * @param description name of item to be packed shown in save file
     */
    public Item(int totalQuantity, int packedQuantity, String description) {
        itemName = description;
        this.packedQuantity = packedQuantity;
        this.totalQuantity = totalQuantity;
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
