//package bagpacker.commands;
//
//import bagpacker.iohandler.Ui;
//import bagpacker.packingfunc.Item;
//import bagpacker.packingfunc.PackingList;
//
//public class UnpackCommand extends Command {
//    private static int unpackQuantity;
//
//    public static final String MSG_SUCCESS_UNPACK = "Item unpacked: %s";
//    public static final String HELP_MSG = "unpack : Marks an item as unpacked in the packing list.\n" +
//            "\tExample: unpack 1 of 2\n" +
//            "\tMeaning: unpacks 1 quantity of the second item in the packing list";
//
//
//    public UnpackCommand(int quantity, int targetIndex) {
//        super(targetIndex);
//        unpackQuantity = quantity;
//        assert (targetIndex >= 1 & targetIndex <= PackingList.getItemList().size()):
//                "Unpack Command Target index is out of bounds";
//    }
//
//    @Override
//    public void execute(PackingList packingList) {
//        this.packingList = packingList;
//        final Item item = getTargetItem();
//        packingList.unpackItem(item, unpackQuantity);
//        Ui.printToUser(String.format(MSG_SUCCESS_UNPACK, item));
//    }
//
//}
