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
     * Constructor of an Item class used after set-up of program
     * @param quantity no. of items user wants to pack
     * @param description name of the item
     */
    public Item(int quantity, String description) {
        this.itemName = description;
        packedQuantity = 0;
        this.totalQuantity = quantity;
    }

    /**
     * Constructor of an Item class used during set-up of program
     * @param totalQuantity total no. of items needed to pack shown in save file
     * @param packedQuantity no. of items currently packed shown in the save file
     * @param description name of item to be packed shown in save file
     */
    public Item(int totalQuantity, int packedQuantity, String description) {
        itemName = description;
        this.packedQuantity = packedQuantity;
        this.totalQuantity = totalQuantity;
    }

    /**
     * Returns whether the item is packed by comparing packedQuantity to totalQuantity
     * @return true if item is packed, false otherwise
     */
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
        assert (packedQuantity <= totalQuantity) :
                "The packed quantity of an item is greater than its total quantity.";
    }

    public void setUnpacked(int quantity) {
        this.packedQuantity -= quantity;
        assert (packedQuantity >= 0) :
                "The packed quantity of an item is smaller than 0.";
    }

    public String toString() {
        return "[" + packedQuantity + "/" + totalQuantity + "] " + this.getItemName();
    }
}
