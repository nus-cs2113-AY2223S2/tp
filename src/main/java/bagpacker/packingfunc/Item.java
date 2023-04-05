package bagpacker.packingfunc;

/**
 * Item class used to store information about item
 * Items to be packed can be saved in PackingList class
 */
public class Item {
    private final String itemName;

    private int packedQuantity;

    private int totalQuantity;

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

    public boolean checkFullyPacked(){
        boolean isFullyPacked = totalQuantity == packedQuantity;
        return isFullyPacked;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getPackedQuantity() {
        return packedQuantity;
    }
    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getUnpackedQuantity() {
        return totalQuantity - packedQuantity;
    }

    public String getItemName() {
        return itemName;
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
