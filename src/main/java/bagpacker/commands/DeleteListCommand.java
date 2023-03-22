package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

import java.util.ArrayList;

public class DeleteListCommand extends Command {
    public static final String MSG_SUCCESS_DELETE_LIST = "list deleted";
    @Override
    public void execute(PackingList packingList) {
        ArrayList< Item > emptyList = new ArrayList<>();
        packingList.setItemList(emptyList);
        Ui.printToUser(String.format(MSG_SUCCESS_DELETE_LIST));
    }



}
