package bagpacker.commands;

import bagpacker.iohandler.Parser;
import bagpacker.packingfunc.PackingList;

public class DeleteListCommand extends Command {

    @Override
    public void execute(PackingList packingList) {
        for (int i = 0; i < packingList.size(); i++) {
            Parser.removeItem(String.valueOf(i), packingList);
        }
    }

}
